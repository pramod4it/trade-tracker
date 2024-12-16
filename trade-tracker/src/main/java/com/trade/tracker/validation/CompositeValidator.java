package com.trade.tracker.validation;

import java.util.HashMap;
import java.util.Map;

public class CompositeValidator {

    private final Map<String, Validator<Object>> validators;

    public CompositeValidator() {
        this.validators = new HashMap<>();
    }

    public void registerValidator(String field, Validator<Object> validator) {
        validators.put(field, validator);
    }

    public void validate(Map<String, Object> inputs) {
        for (Map.Entry<String, Object> entry : inputs.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();

            if (validators.containsKey(field)) {
                validators.get(field).validate(value);
            } else {
                throw new IllegalArgumentException("No validator registered for field: " + field);
            }
        }
    }
}
