package com.highload.stocks.pojo.queue;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class SymbolQueueEntity {
    private String symbol;
    private String exchange;
    private String exchangeSuffix;
    private String exchangeName;
    private String name;
    private LocalDate date;
    private String type;
    private String iexId;
    private String region;
    private String currency;
    private Boolean isEnabled;
    private String figi;
    private String cik;
}
