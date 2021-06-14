package com.highload.stocks.service;

import com.highload.stocks.entity.StockQuote;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public interface StockQuoteTasksQueueService {
    void sendToQueue(List<Supplier<StockQuote>> symbols);

    /**
     * Take next entity.
     * If it end of queue, return empty
     */
    Optional<Supplier<StockQuote>> takeNext();

    /**
     * Take next entities.
     */
    List<Supplier<StockQuote>> takeNext(Long entitiesQuantity);

    int getQueueSize();
}
