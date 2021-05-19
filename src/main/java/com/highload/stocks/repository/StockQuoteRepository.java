package com.highload.stocks.repository;

import com.highload.stocks.entity.StockQuote;

public interface StockQuoteRepository {
    void insertStockQuote(StockQuote stockQuote);
}
