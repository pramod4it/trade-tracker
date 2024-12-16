package com.trade.tracker.service;

import org.springframework.stereotype.Service;

@Service
public class QuantityDecisionService {

    private static final double DEFAULT_BUDGET = 50000.0; // Example: 50,000 INR budget

    public int calculateQuantity(double currentPrice, double momentumStrength) {
        if (currentPrice <= 0) {
            throw new IllegalArgumentException("Current price must be greater than 0.");
        }

        // Adjust budget dynamically based on momentum
        double adjustedBudget = DEFAULT_BUDGET * momentumStrength;

        // Calculate quantity as (adjusted budget / current price), rounded down to nearest integer
        int quantity = (int) (adjustedBudget / currentPrice);

        // Ensure a minimum of 1 quantity is always traded
        return Math.max(quantity, 1);
    }
}
