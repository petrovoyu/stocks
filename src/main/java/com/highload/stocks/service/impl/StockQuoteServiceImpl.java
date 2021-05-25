package com.highload.stocks.service.impl;

import com.highload.stocks.client.IexClient;
import com.highload.stocks.pojo.queue.SymbolQueueEntity;
import com.highload.stocks.service.SymbolQueueService;
import com.highload.stocks.service.StockQuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
class StockQuoteServiceImpl implements StockQuoteService {
    private final SymbolQueueService symbolQueueService;
    private final IexClient iexClient;
    private final ConversionService conversionService;

    @Override
    public void loadStockQuote() {
        iexClient.getSymbols()
                .ifPresent(symbols -> {
                            List<SymbolQueueEntity> symbolQueueEntityList = symbols.stream()
                                    .map(symbol -> conversionService.convert(symbol, SymbolQueueEntity.class))
                                    .collect(Collectors.toList());
                            symbolQueueService.sendToQueue(symbolQueueEntityList);
                        });
    }
}
