package com.highload.stocks.service;

public interface StockQuoteService {
    /**
     * Load stock quotes from external system and send in to internal queue
     */
    void loadStockQuote();


}
