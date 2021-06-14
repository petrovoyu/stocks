package com.highload.stocks.service.impl;

import com.highload.stocks.entity.StockQuote;
import com.highload.stocks.service.StockQuoteTasksQueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;

@Slf4j
@Service
public class InMemoryStockQuoteTasksQueueService implements StockQuoteTasksQueueService {
    private final Queue<Supplier<StockQuote>> queue = new ArrayDeque<>();

    @Override
    public void sendToQueue(List<Supplier<StockQuote>> symbols) {
        queue.addAll(symbols);
    }

    @Override
    public Optional<Supplier<StockQuote>> takeNext() {
        return Optional.ofNullable(queue.poll());
    }

    @Override
    public List<Supplier<StockQuote>> takeNext(Long entitiesQuantity) {
        List<Supplier<StockQuote>> entities = new ArrayList<>();
        for (var i = 0; i < entitiesQuantity; i++) {
            Supplier<StockQuote> nextEntity = queue.poll();
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
