package com.highload.stocks.repository.impl;

import com.highload.stocks.entity.StockQuote;
import com.highload.stocks.repository.StockQuoteRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryStockQuoteRepository implements StockQuoteRepository {
    private static final Map<String, StockQuote> STOCK_QUOTE_CONCURRENT_HASH_MAP = new ConcurrentHashMap<>();

    @Override
    public void insertStockQuote(StockQuote stockQuote) {
        STOCK_QUOTE_CONCURRENT_HASH_MAP.put(stockQuote.getId(), stockQuote);
    }
}
