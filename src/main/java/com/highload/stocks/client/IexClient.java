package com.highload.stocks.client;

import com.highload.stocks.pojo.response.SymbolsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class IexClient {
    private final RestTemplate restTemplate;

    /**
     * This gives the symbols/names/state for each trading company.
     * Reference: [https://iexcloud.io/docs/api/#symbols]
     */
    public SymbolsResponse getSymbols() {
        String url = "https://sandbox.iexapis.com/stable/ref-data/symbols?token=Tpk_ee567917a6b640bb8602834c9d30e571";
        return restTemplate.getForObject(url, SymbolsResponse.class);
    }

    /**
     *  Downloading the current stock information for that company
     *  Reference: https://iexcloud.io/docs/api/#quote
     */
    public String getStockInfo(String stock) {
        String url = "https://sandbox.iexapis.com/stable/stock/{stock code}/quote?token={token}";
        return restTemplate.getForObject(url, String.class, stock, "Tpk_ee567917a6b640bb8602834c9d30e571");
    }
}
