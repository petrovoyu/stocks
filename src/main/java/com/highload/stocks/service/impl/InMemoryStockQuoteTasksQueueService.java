package com.highload.stocks.service.impl;

import com.highload.stocks.entity.StockQuote;
import com.highload.stocks.service.StockQuoteTasksQueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.Callable;

@Slf4j
@Service
public class InMemoryStockQuoteTasksQueueService implements StockQuoteTasksQueueService {
    private final Queue<Callable<StockQuote>> queue = new ArrayDeque<>();

    @Override
    public void sendToQueue(List<Callable<StockQuote>> symbols) {
        queue.addAll(symbols);
    }

    @Override
    public Optional<Callable<StockQuote>> takeNext() {
        return Optional.ofNullable(queue.poll());
    }

    @Override
    public List<Callable<StockQuote>> takeNext(Long entitiesQuantity) {
        List<Callable<StockQuote>> entities = new ArrayList<>();
        for (int i = 0; i < entitiesQuantity; i++) {
            Callable<StockQuote> nextEntity = queue.poll();
            if (nextEntity == null) {
                return entities;
            }

            entities.add(nextEntity);
        }

        return entities;
    }

    @Override
    public int getQueueSize() {
        return queue.size();
    }
}
