package com.highload.stocks.service.impl;

import com.highload.stocks.pojo.queue.SymbolQueueEntity;
import com.highload.stocks.service.SymbolQueueService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InMemorySymbolQueueService implements SymbolQueueService {
    private final Queue<SymbolQueueEntity> queue = new ArrayDeque<>();

    @Override
    public void sendToQueue(List<SymbolQueueEntity> symbols) {
        queue.addAll(symbols);
    }

    @Override
    public Optional<SymbolQueueEntity> takeNext() {
        return Optional.ofNullable(queue.poll());
    }

    @Override
    public List<SymbolQueueEntity> takeNext(Long entitiesQuantity) {
        List<SymbolQueueEntity> entities = new ArrayList<>();
        for (int i = 0; i < entitiesQuantity; i++) {
            SymbolQueueEntity nextEntity = queue.poll();
            if (nextEntity == null) {
                return entities;
            }

            entities.add(nextEntity);
        }

        return entities;
    }
}
