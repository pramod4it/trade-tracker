package com.trade.tracker.validation;

import java.util.Arrays;
import java.util.List;

public class ExchangeValidator implements Validator<Object> {

    private static final List<String> VALID_EXCHANGES = Arrays.asList("NSE", "BSE", "MCX");

    @Override
    public void validate(Object exchange) {
        if (!(exchange instanceof String) || !VALID_EXCHANGES.contains(exchange)) {
            throw new IllegalArgumentException("Invalid exchange: " + exchange);
        }
    }
}
