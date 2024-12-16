package com.trade.tracker.smartapi.smartTicker;

import com.trade.tracker.smartapi.http.exceptions.SmartAPIException;

public interface SmartWSOnError {

	public void onError(Exception exception);

	public void onError(SmartAPIException smartAPIException);

	void onError(String error);
}
 