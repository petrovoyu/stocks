package com.highload.stocks.scheduling;

import com.highload.stocks.entity.StockQuote;
import com.highload.stocks.repository.StockQuoteRepository;
import com.highload.stocks.service.StockQuoteService;
import com.highload.stocks.service.StockQuoteTasksQueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class StockQuoteAsyncProcessing {
    private static final long SYMBOL_BATCH_QUANTITY = 100;
    static final List<Future<StockQuote>> FUTURES = new ArrayList<>();

    private final StockQuoteService stockQuoteService;
    private final StockQuoteTasksQueueService stockQuoteTasksQueueService;
    private final StockQuoteRepository stockQuoteRepository;
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    @Scheduled(fixedDelay = 1)
    public void sendSymbolsToQueueContinuously() {
        stockQuoteService.createAndAddStockQuoteTasksToQueue();
        log.info("Elements in queue count {}", stockQuoteTasksQueueService.getQueueSize());
    }

    @Scheduled(fixedDelay = 1)
    public void loadStockQuoteContinuously() throws InterruptedException {
        List<Callable<StockQuote>> stockQuoteTasks = stockQuoteTasksQueueService.takeNext(SYMBOL_BATCH_QUANTITY);
        FUTURES.addAll(executorService.invokeAll(stockQuoteTasks));
        if (FUTURES.size() > 10) {
            List<Future<StockQuote>> stockQuoteFuturesForRepo = new ArrayList<>(FUTURES);
            FUTURES.clear();
            Thread thread = new Thread(() -> {
                List<StockQuote> stockQuotes = stockQuoteFuturesForRepo.stream()
                        .map(stockQuoteFuture -> {
                            try {
                                return stockQuoteFuture.get();
                            } catch (Exception e) {
                                log.error("Cannot getting stockQuote.", e);
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
                stockQuoteRepository.batchInsertStockQuote(stockQuotes);
                log.info("Symbols in repo {}", stockQuoteRepository.getSymbolsInRepo());
            });

            thread.start();
        }
        log.info("Futures stock quotes {}", StockQuoteAsyncProcessing.FUTURES.size());
    }
}
