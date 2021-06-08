package com.highload.stocks.service;

public interface StockQuoteService {
    /**
     * Load symbols from external system and send it to internal queue
     */
    void createAndAddStockQuoteTasksToQueue();
}
