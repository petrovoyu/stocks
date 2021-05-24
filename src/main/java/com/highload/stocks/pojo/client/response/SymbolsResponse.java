package com.highload.stocks.pojo.client.response;


import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class SymbolsResponse extends ArrayList<SymbolsResponse.Symbol> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class Symbol {
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
}

