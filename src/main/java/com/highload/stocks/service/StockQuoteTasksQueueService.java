package com.highload.stocks.service;

import com.highload.stocks.entity.StockQuote;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

public interface StockQuoteTasksQueueService {
    void sendToQueue(List<Callable<StockQuote>> symbols);

    /**
     * Take next entity.
     * If it end of queue, return empty
     */
    Optional<Callable<StockQuote>> takeNext();

    /**
     * Take next entities.
     */
    List<Callable<StockQuote>> takeNext(Long entitiesQuantity);

    int getQueueSize();
}
