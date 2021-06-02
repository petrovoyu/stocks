package com.highload.stocks.service;

import com.highload.stocks.pojo.queue.SymbolQueueEntity;

import java.util.List;
import java.util.Optional;

public interface SymbolQueueService {
    void sendToQueue(List<SymbolQueueEntity> symbols);

    /**
     * Take next entity.
     * If it end of queue, return empty
     */
    Optional<SymbolQueueEntity> takeNext();

    /**
     * Take next entities.
     */
    List<SymbolQueueEntity> takeNext(Long entitiesQuantity);

    List<String> getSymbolsInQueue();
}
