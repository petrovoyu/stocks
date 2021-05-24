package com.highload.stocks.service.impl;

import com.highload.stocks.pojo.queue.SymbolQueueEntity;
import com.highload.stocks.service.SymbolQueueService;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

@Service
public class InMemorySymbolQueueService implements SymbolQueueService {
    private final Queue<SymbolQueueEntity> queue = new ArrayDeque<>();

    @Override
    public void sendToQueue(List<SymbolQueueEntity> symbols) {
        queue.addAll(symbols);
    }
}
