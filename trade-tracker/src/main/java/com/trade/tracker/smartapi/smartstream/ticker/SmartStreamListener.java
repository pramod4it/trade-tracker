package com.trade.tracker.smartapi.smartstream.ticker;

import com.trade.tracker.smartapi.smartstream.models.*;

public interface SmartStreamListener {
	void onLTPArrival(LTP ltp);
	void onQuoteArrival(Quote quote);
	void onSnapQuoteArrival(SnapQuote snapQuote);

	void onDepthArrival(Depth depth);
	
	void onConnected();
	void onDisconnected();
	void onError(SmartStreamError error);
	void onPong();

	SmartStreamError onErrorCustom();
}