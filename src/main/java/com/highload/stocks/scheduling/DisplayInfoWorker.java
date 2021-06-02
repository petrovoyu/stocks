package com.highload.stocks.scheduling;

import com.highload.stocks.entity.StockQuote;
import com.highload.stocks.repository.StockQuoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class DisplayInfoWorker {
    private final StockQuoteRepository stockQuoteRepository;

    @Scheduled(fixedRate = 5_000)
    public void displayTop5HighestVolumeOfStock() {
        List<StockQuote> stockQuotesOrderByVolume = stockQuoteRepository.getStockQuotesOrderByVolume(5L);
        String info = stockQuotesOrderByVolume.stream()
                .map(stockQuote -> String.format("price %s : stock %s %n", stockQuote.getVolume(), stockQuote.getSymbol()))
                .collect(Collectors.joining());
        log.info(System.lineSeparator() + "{}", info);
    }
}
