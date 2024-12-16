package com.trade.tracker.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Service;

import com.trade.tracker.constant.TradingState;
import com.trade.tracker.manager.TradeManager;

@Service
public class TradingSystemService {

    private final List<TradeManager> tradeManagers;
    private final AtomicReference<TradingState> tradingState = new AtomicReference<>(TradingState.INACTIVE);

    public TradingSystemService(List<TradeManager> tradeManagers) {
        this.tradeManagers = tradeManagers;
    }

    public boolean startTrading() {
        if (tradingState.compareAndSet(TradingState.INACTIVE, TradingState.ACTIVE)) {
            tradeManagers.forEach(TradeManager::start);
            return true;
        }
        return false;
    }

    public boolean stopTrading() {
        if (tradingState.compareAndSet(TradingState.ACTIVE, TradingState.INACTIVE)) {
            tradeManagers.forEach(TradeManager::stop);
            return true;
        }
        return false;
    }

    public TradingState getTradingState() {
        return tradingState.get();
    }
}

