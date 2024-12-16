package com.trade.tracker.utils;

import com.trade.tracker.smartapi.models.OrderParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResponseParser {

    public static List<OrderParams> parseOrderParamsFromResponse(String response) {
        JSONObject jsonResponse = new JSONObject(response);
        return parseOrderParamsFromJson(jsonResponse);
    }

    public static List<OrderParams> parseOrderParamsFromJson(JSONObject jsonResponse) {
        List<OrderParams> orderParamsList = new ArrayList<>();
        JSONArray dataArray = jsonResponse.getJSONArray("data");

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject data = dataArray.getJSONObject(i);
            OrderParams params = new OrderParams();
            params.setExchange(data.optString("exchange"));
            params.setSymbolToken(data.optString("symbolToken"));
            params.setTradingSymbol(data.optString("tradingsymbol"));
            params.setPrice(data.optDouble("ltp", 0.0));
            orderParamsList.add(params);
        }
        return orderParamsList;
    }


    public static List<OrderParams> parseOrderParamsFromString(String response) {
        List<OrderParams> orderParamsList = new ArrayList<>();
        String[] lines = response.split("\n");

        for (String line : lines) {
            if (line.contains("exchange") && line.contains("tradingsymbol") && line.contains("symboltoken")) {
                String[] parts = line.split(",");
                var exchange = parts[0].split(":")[1].strip();
                var tradingsymbol = parts[1].split(":")[1].strip();
                var symboltoken = parts[2].split(":")[1].strip();

                var orderParams = new OrderParams();
                orderParams.setExchange(exchange);
                orderParams.setTradingSymbol(tradingsymbol);
                orderParams.setSymbolToken(symboltoken);

                orderParamsList.add(orderParams);
            }
        }

        return orderParamsList;
    }
}
