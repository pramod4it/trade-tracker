package com.trade.tracker.smartapi.smartTicker;

import org.json.JSONArray;

public interface SmartWSOnTicks {
	void onTicks(JSONArray ticks);
}
