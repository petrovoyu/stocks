package com.highload.stocks.repository.impl;

import com.highload.stocks.entity.StockQuote;
import com.highload.stocks.entity.StockQuoteKey;
import com.highload.stocks.repository.StockQuoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class InMemoryStockQuoteRepository implements StockQuoteRepository {
    private static final Map<StockQuoteKey, StockQuote> STOCK_QUOTE_CONCURRENT_HASH_MAP = new ConcurrentHashMap<>();

    @Override
    public void insertStockQuote(StockQuote stockQuote) {
        StockQuoteKey stockQuoteKey = StockQuoteKey.builder()
                .symbol(stockQuote.getSymbol())
                .latestPrice(stockQuote.getLatestPrice())
                .volume(stockQuote.getVolume() == null ? stockQuote.getPreviousVolume() : stockQuote.getVolume() )
                .build();
        STOCK_QUOTE_CONCURRENT_HASH_MAP.put(stockQuoteKey, stockQuote);
    }

    @Override
    public void batchInsertStockQuote(Collection<StockQuote> stockQuotes) {
        Map<StockQuoteKey, StockQuote> batch = stockQuotes.stream()
                .collect(Collectors.toMap(
                        stockQuote -> StockQuoteKey.builder()
                                .symbol(stockQuote.getSymbol())
                                .latestPrice(stockQuote.getLatestPrice())
                                .volume(stockQuote.getVolume() == null ? stockQuote.getPreviousVolume() : stockQuote.getVolume())
                                .build(),
                        stockQuote -> stockQuote));
        STOCK_QUOTE_CONCURRENT_HASH_MAP.putAll(batch);
    }

    @Override
    public List<StockQuote> getStockQuotesOrderByVolume(Long limitQuantities) {
        return STOCK_QUOTE_CONCURRENT_HASH_MAP.entrySet().stream()
                .filter(entry -> entry.getKey().getVolume() != null)
                .sorted(Comparator
                        .comparingLong(o -> ((Map.Entry<StockQuoteKey, StockQuote>) o).getKey().getVolume())
                        .reversed())
                .limit(limitQuantities)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getSymbolsInRepo() {
        return STOCK_QUOTE_CONCURRENT_HASH_MAP.keySet().stream()
                .map(StockQuoteKey::getSymbol)
                .collect(Collectors.toList());
    }
}
