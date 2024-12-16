package com.trade.tracker.validation;

public interface Validator<T> {
    void validate(T input);
}
