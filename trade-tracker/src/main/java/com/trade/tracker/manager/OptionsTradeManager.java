package com.trade.tracker.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.trade.tracker.service.MarketDataService;
import com.trade.tracker.service.OrderService;
import com.trade.tracker.smartapi.models.OrderParams;
import com.trade.tracker.strategy.GoldenRangeStrategy;

@Component
public class OptionsTradeManager extends BaseTradeManager {

    public OptionsTradeManager(
            MarketDataService marketDataService,
            OrderService orderService,
            GoldenRangeStrategy goldenRangeStrategy,
            @Value("${trade.capital}") double capital,
            @Value("${trade.allocation.percentage}") double allocationPercentage) {
        super(marketDataService, orderService, goldenRangeStrategy, capital, allocationPercentage);
    }

    @Override
    protected String getExchange() {
        return "NSE"; // Example: Options are traded on NSE
    }

    @Override
    protected List<OrderParams> getSymbols() throws Throwable {
        return marketDataService.getOptionSymbols();
    }

    //@Scheduled(fixedRate = 60000) // Every 1 minute
    public void scheduledAnalyzeAndTrade() throws Throwable {
        analyzeAndTrade();
    }
}
