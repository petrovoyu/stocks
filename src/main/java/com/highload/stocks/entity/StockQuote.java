package com.highload.stocks.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class StockQuote {
    private String symbol;
    private String companyName;
    private String primaryExchange;
    private String calculationPrice;
    private BigDecimal open;
    private /* LocalDateTime */ String openTime;
    private String openSource;
    private BigDecimal close;
    private /* LocalDateTime */ String closeTime;
    private String closeSource;
    private BigDecimal high;
    private /* LocalDateTime */ String highTime;
    private String highSource;
    private BigDecimal low;
    private /* LocalDateTime */ String lowTime;
    private String lowSource;
    private BigDecimal latestPrice;
    private String latestSource;
    private String /* LocalDate */ latestTime;
    private /* LocalDateTime */ String latestUpdate;
    private Long latestVolume;
    private BigDecimal iexRealtimePrice;
    private Long iexRealtimeSize;
    private /* LocalDateTime */ String iexLastUpdated;
    private BigDecimal delayedPrice;
    private /* LocalDateTime */ String delayedPriceTime;
    private BigDecimal oddLotDelayedPrice;
    private /* LocalDateTime */ String oddLotDelayedPriceTime;
    private BigDecimal extendedPrice;
    private BigDecimal extendedChange;
    private BigDecimal extendedChangePercent;
    private /* LocalDateTime */ String extendedPriceTime;
    private BigDecimal previousClose;
    private Long previousVolume;
    private BigDecimal change;
    private BigDecimal changePercent;
    private Long volume;
    private BigDecimal iexMarketPercent;
    private Long iexVolume;
    private Long avgTotalVolume;
    private BigDecimal iexBidPrice;
    private Long iexBidSize;
    private BigDecimal iexAskPrice;
    private Long iexAskSize;
    private BigDecimal iexOpen;
    private /* LocalDateTime */ String iexOpenTime;
    private BigDecimal iexClose;
    private /* LocalDateTime */ String iexCloseTime;
    private Long marketCap;
    private BigDecimal peRatio;
    private BigDecimal week52High;
    private BigDecimal week52Low;
    private BigDecimal ytdChange;
    private /* LocalDateTime */ String lastTradeTime;
    private Boolean isUSMarketOpen;
}
