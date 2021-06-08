package com.highload.stocks.scheduling;

import com.highload.stocks.service.StockQuoteService;
import com.highload.stocks.service.SymbolQueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class StockQuoteAsyncProcessing {
    private static final long SYMBOL_BATCH_QUANTITY = 4;

    private final StockQuoteService stockQuoteService;
    private final SymbolQueueService symbolQueueService;
    private final ExecutorService executorService = new ForkJoinPool();

    @Scheduled(fixedDelay = 1)
    public void sendSymbolsToQueueContinuously() {
        stockQuoteService.sendSymbolsToQueue();
    }

    @Scheduled(fixedDelay = 1)
    public void loadStockQuoteContinuously() throws InterruptedException {
        List<Callable<Boolean>> loadStockTasks = symbolQueueService.takeNext(SYMBOL_BATCH_QUANTITY).stream()
                .map(symbolQueueEntity -> (Callable<Boolean>) () -> {
                            try {
                                stockQuoteService.loadStockQuoteToRepositories(symbolQueueEntity);
                            } catch (Exception e) {
                                log.error(e.getMessage());
                            }
                            return true;
                        })
                .collect(Collectors.toList());

        executorService.invokeAll(loadStockTasks);
    }
}
