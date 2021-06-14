package com.highload.stocks.service.impl;

import com.highload.stocks.client.IexClient;
import com.highload.stocks.entity.StockQuote;
import com.highload.stocks.pojo.client.response.SymbolsResponse;
import com.highload.stocks.pojo.queue.SymbolQueueEntity;
import com.highload.stocks.service.StockQuoteService;
import com.highload.stocks.service.StockQuoteTasksQueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
class StockQuoteServiceImpl implements StockQuoteService {
    private final StockQuoteTasksQueueService stockQuoteTasksQueueService;
    private final IexClient iexClient;
    private final ConversionService conversionService;

    @Override
    public void createAndAddStockQuoteTasksToQueue() {
        Optional<SymbolsResponse> symbols = iexClient.getSymbols();
        if (symbols.isPresent()) {
            List<Supplier<StockQuote>> tasks =  symbols.get().stream()
                    .map(symbol -> conversionService.convert(symbol, SymbolQueueEntity.class))
                    .filter(Objects::nonNull)
                    .map(SymbolQueueEntity::getSymbol)
                    .map(this::createsStocksQuoteTask)
                    .collect(Collectors.toList());
            stockQuoteTasksQueueService.sendToQueue(tasks);
        }
    }

    private Supplier<StockQuote> createsStocksQuoteTask(String symbol) {
        return () -> iexClient.getStockQuote(symbol)
                .map(stockQuoteResponse -> conversionService.convert(stockQuoteResponse, StockQuote.class))
                .orElse(null);
    }
}
