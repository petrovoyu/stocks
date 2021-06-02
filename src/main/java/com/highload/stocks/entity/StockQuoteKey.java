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
public class StockQuoteKey {
    private String symbol;
    private Long volume;
    private BigDecimal latestPrice;
}
