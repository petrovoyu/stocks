package com.highload.stocks.client;

import com.highload.stocks.pojo.client.response.StockQuoteResponse;
import com.highload.stocks.pojo.client.response.SymbolsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class IexClient {
    private final RestTemplate restTemplate;

    public IexClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.errorHandler(new IexResponseErrorHandler())
                .build();
    }

    /**
     * This gives the symbols/names/state for each trading company.
     * Reference: [https://iexcloud.io/docs/api/#symbols]
     */
    public Optional<SymbolsResponse> getSymbols() {
        String url = "https://sandbox.iexapis.com/stable/ref-data/symbols?token=Tpk_ee567917a6b640bb8602834c9d30e571";
        SymbolsResponse symbolsResponse = restTemplate.getForObject(url, SymbolsResponse.class);
        return Optional.ofNullable(symbolsResponse);
    }

    /**
     *  Downloading the current stock information for that company
     *  Reference: https://iexcloud.io/docs/api/#quote
     */
    public Optional<StockQuoteResponse> getStockQuote(String stock) {
        String url = "https://sandbox.iexapis.com/stable/stock/{stock code}/quote?token={token}";
        return Optional.ofNullable(restTemplate.getForObject(url, StockQuoteResponse.class, stock, "Tpk_ee567917a6b640bb8602834c9d30e571"));
    }

    private static final class IexResponseErrorHandler implements ResponseErrorHandler {

        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            return response.getStatusCode() != HttpStatus.OK;
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            log.error(response.getStatusText());
        }
    }
}
