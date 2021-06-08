package com.highload.stocks.service.impl;

import com.highload.stocks.client.IexClient;
import com.highload.stocks.entity.StockQuote;
import com.highload.stocks.pojo.queue.SymbolQueueEntity;
import com.highload.stocks.repository.StockQuoteRepository;
import com.highload.stocks.service.StockQuoteService;
import com.highload.stocks.service.SymbolQueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
class StockQuoteServiceImpl implements StockQuoteService {
    private final SymbolQueueService symbolQueueService;
    private final IexClient iexClient;
    private final ConversionService conversionService;
    private final StockQuoteRepository stockQuoteRepository;

    @Override
    public void loadStockQuoteToRepositories(@NonNull SymbolQueueEntity symbolQueueEntity) {
        iexClient.getStockQuote(symbolQueueEntity.getSymbol())
                .map(stockQuoteResponse -> conversionService.convert(stockQuoteResponse, StockQuote.class))
                .ifPresent(stockQuoteRepository::insertStockQuote);
    }

    @Override
    public void sendSymbolsToQueue() {
        iexClient.getSymbols()
                .ifPresent(symbols -> {
                    List<SymbolQueueEntity> symbolQueueEntityList = symbols.stream()
                            .map(symbol -> conversionService.convert(symbol, SymbolQueueEntity.class))
                            .collect(Collectors.toList());
                    symbolQueueService.sendToQueue(symbolQueueEntityList);
                });
    }
}
