package com.trade.tracker.smartapi.ticker;

import org.json.JSONArray;

public interface OnTicks {
	void onTicks(JSONArray ticks);
}
