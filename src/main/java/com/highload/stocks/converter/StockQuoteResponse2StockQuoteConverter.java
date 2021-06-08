package com.highload.stocks.converter;

import com.highload.stocks.entity.StockQuote;
import com.highload.stocks.pojo.client.response.StockQuoteResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class StockQuoteResponse2StockQuoteConverter implements Converter<StockQuoteResponse, StockQuote> {
    @Nullable
    @Override
    public StockQuote convert(@Nullable StockQuoteResponse source) {
        if (source == null) {
            return null;
        }
        return StockQuote.builder()
                .avgTotalVolume(source.getAvgTotalVolume())
                .calculationPrice(source.getCalculationPrice())
                .change(source.getChange())
                .changePercent(source.getChangePercent())
                .close(source.getClose())
                .closeSource(source.getCloseSource())
                .closeTime(source.getCloseTime())
                .companyName(source.getCompanyName())
                .delayedPrice(source.getDelayedPrice())
                .delayedPriceTime(source.getDelayedPriceTime())
                .extendedChange(source.getExtendedChange())
                .extendedChangePercent(source.getExtendedChangePercent())
                .extendedPrice(source.getExtendedPrice())
                .extendedPriceTime(source.getExtendedPriceTime())
                .high(source.getHigh())
                .highSource(source.getHighSource())
                .highTime(source.getHighTime())
                .iexAskPrice(source.getIexAskPrice())
                .iexAskSize(source.getIexAskSize())
                .iexBidPrice(source.getIexBidPrice())
                .iexBidSize(source.getIexBidSize())
                .iexClose(source.getIexClose())
                .iexCloseTime(source.getIexCloseTime())
                .iexLastUpdated(source.getIexLastUpdated())
                .iexMarketPercent(source.getIexMarketPercent())
                .iexOpen(source.getIexOpen())
                .iexOpenTime(source.getIexOpenTime())
                .iexRealtimePrice(source.getIexRealtimePrice())
                .iexRealtimeSize(source.getIexRealtimeSize())
                .iexVolume(source.getIexVolume())
                .isUSMarketOpen(source.getIsUSMarketOpen())
                .lastTradeTime(source.getLastTradeTime())
                .latestPrice(source.getLatestPrice())
                .latestSource(source.getLatestSource())
                .latestTime(source.getLatestTime())
                .latestUpdate(source.getLatestUpdate())
                .latestVolume(source.getLatestVolume())
                .low(source.getLow())
                .lowSource(source.getLowSource())
                .lowTime(source.getLowTime())
                .marketCap(source.getMarketCap())
                .oddLotDelayedPrice(source.getOddLotDelayedPrice())
                .oddLotDelayedPriceTime(source.getOddLotDelayedPriceTime())
                .open(source.getOpen())
                .openSource(source.getOpenSource())
                .openTime(source.getOpenTime())
                .peRatio(source.getPeRatio())
                .previousClose(source.getPreviousClose())
                .previousVolume(source.getPreviousVolume())
                .primaryExchange(source.getPrimaryExchange())
                .symbol(source.getSymbol())
                .volume(source.getVolume())
                .week52High(source.getWeek52High())
                .week52Low(source.getWeek52Low())
                .ytdChange(source.getYtdChange())
                .build();
    }
}
