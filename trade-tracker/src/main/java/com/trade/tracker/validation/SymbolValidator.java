package com.trade.tracker.validation;

public class SymbolValidator implements Validator<Object> {

    @Override
    public void validate(Object symbol) {
        if (!(symbol instanceof String) || ((String) symbol).isEmpty()) {
            throw new IllegalArgumentException("Invalid symbol: " + symbol);
        }
    }
}
