package com.trade.tracker.strategy;

import org.springframework.stereotype.Component;

import com.trade.tracker.smartapi.models.OrderParams;
import com.trade.tracker.service.MarketDataService;

@Component
public class GoldenRangeStrategy {

    private final MarketDataService marketDataService;

    public GoldenRangeStrategy(MarketDataService marketDataService) {
        this.marketDataService = marketDataService;
    }

    public boolean shouldBuy(OrderParams orderParams) throws Throwable {
        double currentPrice = orderParams.getPrice();
        double lowerBound = calculateGoldenRangeLower(orderParams);
        double rsi = marketDataService.getRSI(orderParams.getExchange(), orderParams.getTradingSymbol());

        return currentPrice <= lowerBound && rsi < 30;
    }

    public boolean shouldSell(OrderParams orderParams) throws Throwable {
        double currentPrice = orderParams.getPrice();
        double upperBound = calculateGoldenRangeUpper(orderParams);
        double rsi = marketDataService.getRSI(orderParams.getExchange(), orderParams.getTradingSymbol());

        return currentPrice >= upperBound && rsi > 70;
    }

    private double calculateGoldenRangeLower(OrderParams orderParams) throws Throwable {
        double sma20 = marketDataService.getSMA(orderParams.getExchange(), orderParams.getTradingSymbol(), 20);
        return sma20 * 0.95; // Example: Lower bound is 95% of SMA
    }

    private double calculateGoldenRangeUpper(OrderParams orderParams) throws Throwable {
        double sma20 = marketDataService.getSMA(orderParams.getExchange(), orderParams.getTradingSymbol(), 20);
        return sma20 * 1.05; // Example: Upper bound is 105% of SMA
    }
}
