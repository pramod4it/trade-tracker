package com.trade.tracker.smartapi.ticker;

import com.trade.tracker.smartapi.http.exceptions.SmartAPIException;

public interface OnError {

	public void onError(Exception exception);

	public void onError(SmartAPIException smartAPIException);

	void onError(String error);
}
