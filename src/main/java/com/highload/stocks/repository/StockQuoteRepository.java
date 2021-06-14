package com.highload.stocks.repository;

import com.highload.stocks.entity.StockQuote;

import java.util.Collection;
import java.util.List;

public interface StockQuoteRepository {
    void insertStockQuote(StockQuote stockQuote);
    void batchInsertStockQuote(Collection<StockQuote> stockQuotes);
    List<StockQuote> getStockQuotesOrderByVolume(Long limitQuantities);
    List<String> getSymbolsInRepo();
}
