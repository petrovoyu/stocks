package com.highload.stocks.client;

import com.highload.stocks.pojo.response.SymbolsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class IexClientTest {
    @Autowired
    private IexClient iexClient;

    @Test
    void getTradingCompanies() {
        SymbolsResponse symbols = iexClient.getSymbols();
        assertFalse(symbols.isEmpty());
    }

    @Test
    void getStockInfo() {
        assertFalse(iexClient.getStockInfo("MSFT").isEmpty());
    }
}