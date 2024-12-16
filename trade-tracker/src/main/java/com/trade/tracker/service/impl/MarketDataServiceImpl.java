package com.trade.tracker.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.trade.tracker.smartapi.SmartConnect;
import com.trade.tracker.smartapi.models.OrderParams;
import com.trade.tracker.service.MarketDataService;
import com.trade.tracker.utils.PayloadFactory;
import com.trade.tracker.utils.ResponseParser;
import com.trade.tracker.validation.CompositeValidator;
import com.trade.tracker.validation.ExchangeValidator;
import com.trade.tracker.validation.SymbolValidator;

@Service
public class MarketDataServiceImpl implements MarketDataService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MarketDataServiceImpl.class);

	private final SmartConnect apiClient;
	private final CompositeValidator compositeValidator;

	public MarketDataServiceImpl(SmartConnect apiClient) {
		this.apiClient = apiClient;
		this.compositeValidator = new CompositeValidator();
		this.compositeValidator.registerValidator("exchange", new ExchangeValidator());
		this.compositeValidator.registerValidator("symbol", new SymbolValidator());
	}

	@Override
	public List<OrderParams> getNSESymbols() throws Throwable {
		LOGGER.info("Fetching NSE symbols...");
		String response = apiClient.getSearchScrip(PayloadFactory.createSearchPayload("NSE", "RELIANCE"));
		return ResponseParser.parseOrderParamsFromString(response);
	}

	@Override
	public List<OrderParams> getBSESymbols() throws Throwable {
		LOGGER.info("Fetching BSE symbols...");
		String response = apiClient.getSearchScrip(PayloadFactory.createSearchPayload("BSE", "RELIANCE"));
		return ResponseParser.parseOrderParamsFromString(response);
	}

	@Override
	public double getCurrentPrice(String exchange, String symbol) throws Throwable {
		validateInputs(exchange, symbol);
		LOGGER.info("Fetching current price for {} on {}", symbol, exchange);
		JSONObject response = apiClient.getLTP(exchange, symbol, "");
		return response.optDouble("ltp", 0.0);
	}

	@Override
	public List<OrderParams> getTopGainers(String exchange) throws Throwable {
		LOGGER.info("Fetching top 10 gainers for exchange: {}", exchange);

		String dataType = "PercOIGainers";
		String expiryType = "NEAR"; // You can choose "NEAR", "NEXT", or "FAR"

		try {
			JSONObject response = apiClient
					.gainersLosers(PayloadFactory.createGainersLosersPayload(exchange, dataType, expiryType));

			List<OrderParams> allGainers = ResponseParser.parseOrderParamsFromJson(response);

			// Calculate and sort gainers based on percentage change
			return allGainers.stream().map(gainer -> {
				// Assuming the API response includes previous close price as
				// "previousClosePrice"
				double currentPrice = gainer.getPrice(); // Current price of the stock
				double previousClosePrice = gainer.getPreviousClosePrice(); // Previous close price

				double percentageChange = calculatePercentageChange(currentPrice, previousClosePrice);
				gainer.setPercentageChange(percentageChange); // Set percentage change in the OrderParams
				return gainer;
			}).sorted(
					(gainer1, gainer2) -> Double.compare(gainer2.getPercentageChange(), gainer1.getPercentageChange()))
					.limit(10).collect(Collectors.toList());

		} catch (Exception e) {
			LOGGER.error("Error while fetching top gainers for {}: ", exchange, e);
			throw new RuntimeException("Error fetching top gainers", e);
		}
	}

	// Method to calculate percentage change
	private double calculatePercentageChange(double currentPrice, double previousClosePrice) {
		if (previousClosePrice == 0) {
			return 0;
		}
		return ((currentPrice - previousClosePrice) / previousClosePrice) * 100;
	}

	@Override
	public JSONObject getOptionDelta(String symbol) throws Throwable {
		validateInputs(null, symbol);
		LOGGER.info("Fetching option delta for {}", symbol);
		return apiClient.optionGreek(PayloadFactory.createOptionPayload(symbol));
	}

	@Override
	public List<OrderParams> getOptionSymbols() throws Throwable {
		LOGGER.info("Fetching option symbols...");
		JSONObject response = apiClient
				.gainersLosers(PayloadFactory.createGainersLosersPayload("NFO", "PercOIGainers", "NEAR"));
		return ResponseParser.parseOrderParamsFromJson(response);
	}

	@Override
	public double getRSI(String exchange, String symbol) throws Throwable {
		validateInputs(exchange, symbol);
		LOGGER.info("Fetching RSI for {} on {}", symbol, exchange);
		JSONObject response = apiClient.optionGreek(PayloadFactory.createOptionPayload(symbol));
		return response.optDouble("rsi", 0.0);
	}

	@Override
	public double getSMA(String exchange, String symbol, int period) throws Throwable {
		validateInputs(exchange, symbol);
		LOGGER.info("Fetching {}-day SMA for {} on {}", period, symbol, exchange);
		JSONObject response = apiClient.optionGreek(PayloadFactory.createOptionPayload(symbol));
		return response.optDouble("sma_" + period, 0.0);
	}

	@Override
	public List<OrderParams> getNearGainers(String exchange) throws Throwable {
		validateInputs(exchange, null);
		LOGGER.info("Fetching near gainers for {}", exchange);
		JSONObject response = apiClient
				.gainersLosers(PayloadFactory.createGainersLosersPayload(exchange, "PercOIGainers", "NEAR"));
		return ResponseParser.parseOrderParamsFromJson(response);
	}

	@Override
	public List<OrderParams> getNearLosers(String exchange) throws Throwable {
		validateInputs(exchange, null);
		LOGGER.info("Fetching near losers for {}", exchange);
		JSONObject response = apiClient
				.gainersLosers(PayloadFactory.createGainersLosersPayload(exchange, "PercOILosers", "NEAR"));
		return ResponseParser.parseOrderParamsFromJson(response);
	}

	private void validateInputs(String exchange, String symbol) {
		Map<String, Object> inputs = new HashMap<>();
		inputs.put("exchange", exchange);
		inputs.put("symbol", symbol);
		compositeValidator.validate(inputs);
	}
}
