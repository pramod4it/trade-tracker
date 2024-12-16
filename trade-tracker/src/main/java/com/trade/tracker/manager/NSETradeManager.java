package com.trade.tracker.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.trade.tracker.smartapi.models.OrderParams;
import com.trade.tracker.service.MarketDataService;
import com.trade.tracker.service.OrderService;
import com.trade.tracker.strategy.GoldenRangeStrategy;

@Component
public class NSETradeManager extends BaseTradeManager {

    public NSETradeManager(
            MarketDataService marketDataService,
            OrderService orderService,
            GoldenRangeStrategy goldenRangeStrategy,
            @Value("${trade.capital}") double capital,
            @Value("${trade.allocation.percentage}") double allocationPercentage) {
        super(marketDataService, orderService, goldenRangeStrategy, capital, allocationPercentage);
    }

    @Override
    protected String getExchange() {
        return "NSE";
    }

    @Override
    protected List<OrderParams> getSymbols() throws Throwable {
        return marketDataService.getNSESymbols();
    }

    //@Scheduled(fixedRate = 60000) // Every 1 minute
    public void scheduledAnalyzeAndTrade() throws Throwable {
        analyzeAndTrade();
    }
}
