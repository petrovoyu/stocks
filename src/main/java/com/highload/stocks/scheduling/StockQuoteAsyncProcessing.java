package com.highload.stocks.scheduling;

import com.highload.stocks.entity.StockQuote;
import com.highload.stocks.repository.StockQuoteRepository;
import com.highload.stocks.service.StockQuoteService;
import com.highload.stocks.service.StockQuoteTasksQueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

@Slf4j
@RequiredArgsConstructor
@Component
public class StockQuoteAsyncProcessing {
    private static final long SYMBOL_BATCH_QUANTITY = 2;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(4);
    static final BlockingDeque<StockQuote> stockQuotes = new LinkedBlockingDeque<>();

    private final StockQuoteService stockQuoteService;
    private final StockQuoteTasksQueueService stockQuoteTasksQueueService;
    private final StockQuoteRepository stockQuoteRepository;

    @Scheduled(fixedDelay = 1)
    public void sendSymbolsToQueueContinuously() {
        stockQuoteService.createAndAddStockQuoteTasksToQueue();
        log.info("Elements in queue count {}", stockQuoteTasksQueueService.getQueueSize());
    }

    @Scheduled(fixedDelay = 1)
    public void loadStockQuoteContinuously() throws InterruptedException {
        List<Supplier<StockQuote>> stockQuoteTasks = stockQuoteTasksQueueService.takeNext(SYMBOL_BATCH_QUANTITY);
        stockQuoteTasks.forEach(stockQuoteCallable -> CompletableFuture.supplyAsync(stockQuoteCallable, executorService)
            .thenAcceptAsync(stockQuotes::add));

        if (stockQuotes.size() > 10) {
            log.info("Futures stock quotes {}", stockQuotes.size());
            BlockingDeque<StockQuote> stockQuotesForRepo = new LinkedBlockingDeque<>(stockQuotes);
            var thread = new Thread(() -> {
                stockQuoteRepository.batchInsertStockQuote(stockQuotesForRepo);
                log.info("Symbols in repo {}", stockQuoteRepository.getSymbolsInRepo());
                stockQuotes.removeAll(stockQuotesForRepo);
            }, "Stock quote batch insert");

            thread.start();
        }
    }
}
