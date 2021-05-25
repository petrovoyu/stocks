package com.highload.stocks.service;

import com.highload.stocks.pojo.queue.SymbolQueueEntity;

import java.util.List;

public interface SymbolQueueService {
    void sendToQueue(List<SymbolQueueEntity> symbols);
}
