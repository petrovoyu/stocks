package com.highload.stocks.pojo.client.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class StockQuoteResponseTest {
    @Autowired
    private ObjectMapper jsonMapper;

    private static final String latestTimeRaw = "December 8, 2020";
    private static final String STOCK_QUOTE_RAW = "{\n" +
            "  \"symbol\": \"BAC\",\n" +
            "  \"companyName\": \"Bank Of America Corp.\",\n" +
            "  \"primaryExchange\": \"NEW YORK STOCK EXCHANGE, INC.\",\n" +
            "  \"calculationPrice\": \"close\",\n" +
            "  \"open\": 28.81,\n" +
            "  \"openTime\": 1607437801023,\n" +
            "  \"openSource\": \"official\",\n" +
            "  \"close\": 28.81,\n" +
            "  \"closeTime\": 1607461201852,\n" +
            "  \"closeSource\": \"official\",\n" +
            "  \"high\": 29.12,\n" +
            "  \"highTime\": 1607461198592,\n" +
            "  \"highSource\": \"15 minute delayed price\",\n" +
            "  \"low\": 27.68,\n" +
            "  \"lowTime\": 1607437803011,\n" +
            "  \"lowSource\": \"15 minute delayed price\",\n" +
            "  \"latestPrice\": 28.81,\n" +
            "  \"latestSource\": \"Close\",\n" +
            "  \"latestTime\": \"" + latestTimeRaw + "\",\n" +
            "  \"latestUpdate\": 1607461201852,\n" +
            "  \"latestVolume\": 33820759,\n" +
            "  \"iexRealtimePrice\": 28.815,\n" +
            "  \"iexRealtimeSize\": 100,\n" +
            "  \"iexLastUpdated\": 1607461192396,\n" +
            "  \"delayedPrice\": 28.82,\n" +
            "  \"delayedPriceTime\": 1607461198592,\n" +
            "  \"oddLotDelayedPrice\": 28.82,\n" +
            "  \"oddLotDelayedPriceTime\": 1607461198391,\n" +
            "  \"extendedPrice\": 28.93,\n" +
            "  \"extendedChange\": 0.04,\n" +
            "  \"extendedChangePercent\": 0.00137,\n" +
            "  \"extendedPriceTime\": 1607471631362,\n" +
            "  \"previousClose\": 29.49,\n" +
            "  \"previousVolume\": 42197768,\n" +
            "  \"change\": -0.16,\n" +
            "  \"changePercent\": -0.0045,\n" +
            "  \"volume\": 33820759,\n" +
            "  \"iexMarketPercent\": 0.01709376134658947,\n" +
            "  \"iexVolume\": 578127,\n" +
            "  \"avgTotalVolume\": 60029202,\n" +
            "  \"iexBidPrice\": 0,\n" +
            "  \"iexBidSize\": 0,\n" +
            "  \"iexAskPrice\": 0,\n" +
            "  \"iexAskSize\": 0,\n" +
            "  \"iexOpen\": 28.815,\n" +
            "  \"iexOpenTime\": 1607461192355,\n" +
            "  \"iexClose\": 28.815,\n" +
            "  \"iexCloseTime\": 1607461192355,\n" +
            "  \"marketCap\": 2502673458439,\n" +
            "  \"peRatio\": 14.23,\n" +
            "  \"week52High\": 34.68,\n" +
            "  \"week52Low\": 17.50,\n" +
            "  \"ytdChange\": -0.1573975163337491,\n" +
            "  \"lastTradeTime\": 1607461198587,\n" +
            "  \"isUSMarketOpen\": false\n" +
            "}";

    @Test
    void shouldParseRaw() throws JsonProcessingException {
        StockQuoteResponse stockQuoteResponse = jsonMapper.readValue(STOCK_QUOTE_RAW, StockQuoteResponse.class);
        assertNotNull(stockQuoteResponse);
    }
}