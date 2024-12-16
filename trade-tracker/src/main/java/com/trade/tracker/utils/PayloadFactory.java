package com.trade.tracker.utils;

import java.time.LocalDateTime;
import org.json.JSONObject;

public class PayloadFactory {

	public static JSONObject createSearchPayload(String exchange, String searchQuery) {
		JSONObject payload = new JSONObject();
		payload.put("exchange", exchange);
		payload.put("searchscrip", searchQuery);
		return payload;
	}

	public static JSONObject createGainersLosersPayload(String exchange, String dataType, String expirytype) {
		JSONObject payload = new JSONObject();
		payload.put("datatype", dataType);
		payload.put("expirytype", expirytype);
		payload.put("exchange", exchange);
		return payload;
	}

	public static JSONObject createOptionPayload(String symbol) {
		JSONObject payload = new JSONObject();
		payload.put("name", symbol);
		payload.put("expirydate", DateTimeUtil.formatDate(LocalDateTime.now()));
		return payload;
	}
}
