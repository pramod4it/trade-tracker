package com.trade.tracker.manager;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trade.tracker.smartapi.models.OrderParams;
import com.trade.tracker.service.MarketDataService;
import com.trade.tracker.service.OrderService;
import com.trade.tracker.strategy.GoldenRangeStrategy;

public abstract class BaseTradeManager implements TradeManager {

    private static final Logger logger = LoggerFactory.getLogger(BaseTradeManager.class);

    protected final MarketDataService marketDataService;
    protected final OrderService orderService;
    protected final GoldenRangeStrategy goldenRangeStrategy;
    private final AtomicBoolean isActive = new AtomicBoolean(false);
    private Thread tradeThread;

    private final double capital;
    private final double allocationPercent;

    public BaseTradeManager(MarketDataService marketDataService, OrderService orderService,
                            GoldenRangeStrategy goldenRangeStrategy, double capital, double allocationPercent) {
        this.marketDataService = marketDataService;
        this.orderService = orderService;
        this.goldenRangeStrategy = goldenRangeStrategy;
        this.capital = capital;
        this.allocationPercent = allocationPercent;
    }

    protected abstract String getExchange();

    protected abstract List<OrderParams> getSymbols() throws Throwable;

    protected void analyzeAndTrade() throws Throwable {
        for (OrderParams symbol : getSymbols()) {
            if (goldenRangeStrategy.shouldBuy(symbol)) {
                placeOrder(symbol, "BUY");
            } else if (goldenRangeStrategy.shouldSell(symbol)) {
                placeOrder(symbol, "SELL");
            }
        }
    }

    private void placeOrder(OrderParams orderParams, String orderType) throws Throwable {
        int quantity = determineQuantity(orderParams);
        orderParams.setQuantity(quantity);
        orderService.placeOrder(orderParams);
    }

    private int determineQuantity(OrderParams symbol) throws Throwable {
        double currentPrice = symbol.getPrice();
        return (int) (capital * allocationPercent / currentPrice);
    }

    @Override
    public void start() {
        if (tradeThread == null || !tradeThread.isAlive()) {
            isActive.set(true);
            tradeThread = new Thread(() -> {
                int backoff = 1000;
                while (isActive.get()) {
                    try {
                        analyzeAndTrade();
                        Thread.sleep(1000);
                        backoff = 1000;
                    } catch (Throwable e) {
                        logger.error("Error during trading loop: ", e);
                        try {
                            Thread.sleep(backoff);
                            backoff = Math.min(backoff * 2, 30000);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }
            });
            tradeThread.start();
        }
    }

    @Override
    public void stop() {
        isActive.set(false);
        if (tradeThread != null) {
            try {
                tradeThread.join();
            } catch (InterruptedException e) {
                logger.error("Error stopping trading loop: ", e);
                Thread.currentThread().interrupt();
            }
        }
    }
}
