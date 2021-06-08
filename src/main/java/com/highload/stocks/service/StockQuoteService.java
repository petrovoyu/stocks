package com.highload.stocks.service;

import com.highload.stocks.pojo.queue.SymbolQueueEntity;
import org.springframework.lang.NonNull;

public interface StockQuoteService {
    /**
     * Load symbols from internal queue, convert it to stock quote and add to repositories
     */
    void loadStockQuoteToRepositories(@NonNull SymbolQueueEntity symbolQueueEntity);

    /**
     * Load symbols from external system and send it to internal queue
     */
    void sendSymbolsToQueue();
}
