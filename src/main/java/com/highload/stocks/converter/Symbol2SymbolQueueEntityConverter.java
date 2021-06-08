package com.highload.stocks.converter;

import com.highload.stocks.pojo.client.response.SymbolsResponse;
import com.highload.stocks.pojo.queue.SymbolQueueEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class Symbol2SymbolQueueEntityConverter implements Converter<SymbolsResponse.Symbol, SymbolQueueEntity> {
    @Nullable
    @Override
    public SymbolQueueEntity convert(@Nullable SymbolsResponse.Symbol source) {
        if (source == null) {
            return null;
        }

        return SymbolQueueEntity.builder()
                .cik(source.getCik())
                .currency(source.getCurrency())
                .date(source.getDate())
                .figi(source.getFigi())
                .iexId(source.getIexId())
                .isEnabled(source.getIsEnabled())
                .name(source.getName())
                .region(source.getRegion())
                .type(source.getType())
                .symbol(source.getSymbol())
                .exchange(source.getExchange())
                .exchangeSuffix(source.getExchangeSuffix())
                .exchangeName(source.getExchangeName())
                .build();
    }
}
