package com.highload.stocks.client;

import com.highload.stocks.pojo.client.response.StockQuoteResponse;
import com.highload.stocks.pojo.client.response.SymbolsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class IexClientTest {
    @Autowired
    private IexClient iexClient;

    @Test
    void getTradingCompanies() {
        Optional<SymbolsResponse> symbols = iexClient.getSymbols();
        assertFalse(symbols.isEmpty());
        System.err.println(symbols.get());
    }

    @Test
    void getStockInfo() {
        Optional<StockQuoteResponse> stockQuote = iexClient.getStockQuote("MSFT");
        assertFalse(stockQuote.isEmpty());
        System.err.println(stockQuote.get());
    }
}