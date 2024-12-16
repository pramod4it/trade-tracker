package com.trade.tracker.service;

import org.json.JSONObject;
import com.trade.tracker.smartapi.models.OrderParams;
import java.util.List;

public interface MarketDataService {
	List<OrderParams> getNSESymbols() throws Throwable;

	List<OrderParams> getBSESymbols() throws Throwable;

	List<OrderParams> getOptionSymbols() throws Throwable;

	double getRSI(String exchange, String symbol) throws Throwable;

	double getSMA(String exchange, String symbol, int period) throws Throwable;

	double getCurrentPrice(String exchange, String symbol) throws Throwable;

	List<OrderParams> getNearGainers(String exchange) throws Throwable;

	List<OrderParams> getNearLosers(String exchange) throws Throwable;

	JSONObject getOptionDelta(String symbol) throws Throwable;

	List<OrderParams> getTopGainers(String exchange) throws Throwable; // New method to get top gainers
}
