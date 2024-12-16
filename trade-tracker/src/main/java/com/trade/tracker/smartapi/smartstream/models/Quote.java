package com.trade.tracker.smartapi.smartstream.models;

import static com.trade.tracker.smartapi.utils.Constants.AVG_TRADED_PRICE_OFFSET;
import static com.trade.tracker.smartapi.utils.Constants.CLOSE_PRICE_OFFSET;
import static com.trade.tracker.smartapi.utils.Constants.EXCHANGE_FEED_TIME_OFFSET;
import static com.trade.tracker.smartapi.utils.Constants.HIGH_PRICE_OFFSET;
import static com.trade.tracker.smartapi.utils.Constants.LAST_TRADED_PRICE_OFFSET;
import static com.trade.tracker.smartapi.utils.Constants.LAST_TRADED_QTY_OFFSET;
import static com.trade.tracker.smartapi.utils.Constants.LOW_PRICE_OFFSET;
import static com.trade.tracker.smartapi.utils.Constants.OPEN_PRICE_OFFSET;
import static com.trade.tracker.smartapi.utils.Constants.SEQUENCE_NUMBER_OFFSET;
import static com.trade.tracker.smartapi.utils.Constants.SUBSCRIPTION_MODE;
import static com.trade.tracker.smartapi.utils.Constants.TOTAL_BUY_QTY_OFFSET;
import static com.trade.tracker.smartapi.utils.Constants.TOTAL_SELL_QTY_OFFSET;
import static com.trade.tracker.smartapi.utils.Constants.VOLUME_TRADED_TODAY_OFFSET;

import java.nio.ByteBuffer;

import com.trade.tracker.smartapi.utils.ByteUtils;

public class Quote {
	private byte subscriptionMode;
	private ExchangeType exchangeType;
	private TokenID token;
	private long sequenceNumber;
	private long exchangeFeedTimeEpochMillis;
	private long lastTradedPrice;
	private long lastTradedQty;
	private long avgTradedPrice;
	private long volumeTradedToday;
	private double totalBuyQty;
	private double totalSellQty;
	private long openPrice;
	private long highPrice;
	private long lowPrice;
	private long closePrice;

	public Quote(ByteBuffer buffer) {
        this.subscriptionMode = buffer.get(SUBSCRIPTION_MODE);
        this.token = ByteUtils.getTokenID(buffer);
        this.exchangeType = this.token.getExchangeType();
        this.sequenceNumber = buffer.getLong(SEQUENCE_NUMBER_OFFSET);
        this.exchangeFeedTimeEpochMillis = buffer.getLong(EXCHANGE_FEED_TIME_OFFSET);
        this.lastTradedPrice = buffer.getLong(LAST_TRADED_PRICE_OFFSET);
        this.lastTradedQty = buffer.getLong(LAST_TRADED_QTY_OFFSET);
        this.avgTradedPrice = buffer.getLong(AVG_TRADED_PRICE_OFFSET);
        this.volumeTradedToday = buffer.getLong(VOLUME_TRADED_TODAY_OFFSET);
        this.totalBuyQty = buffer.getLong(TOTAL_BUY_QTY_OFFSET);
        this.totalSellQty = buffer.getLong(TOTAL_SELL_QTY_OFFSET);
        this.openPrice = buffer.getLong(OPEN_PRICE_OFFSET);
        this.highPrice = buffer.getLong(HIGH_PRICE_OFFSET);
        this.lowPrice = buffer.getLong(LOW_PRICE_OFFSET);
        this.closePrice = buffer.getLong(CLOSE_PRICE_OFFSET);
    }

	public byte getSubscriptionMode() {
		return subscriptionMode;
	}

	public void setSubscriptionMode(byte subscriptionMode) {
		this.subscriptionMode = subscriptionMode;
	}

	public ExchangeType getExchangeType() {
		return exchangeType;
	}

	public void setExchangeType(ExchangeType exchangeType) {
		this.exchangeType = exchangeType;
	}

	public TokenID getToken() {
		return token;
	}

	public void setToken(TokenID token) {
		this.token = token;
	}

	public long getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public long getExchangeFeedTimeEpochMillis() {
		return exchangeFeedTimeEpochMillis;
	}

	public void setExchangeFeedTimeEpochMillis(long exchangeFeedTimeEpochMillis) {
		this.exchangeFeedTimeEpochMillis = exchangeFeedTimeEpochMillis;
	}

	public long getLastTradedPrice() {
		return lastTradedPrice;
	}

	public void setLastTradedPrice(long lastTradedPrice) {
		this.lastTradedPrice = lastTradedPrice;
	}

	public long getLastTradedQty() {
		return lastTradedQty;
	}

	public void setLastTradedQty(long lastTradedQty) {
		this.lastTradedQty = lastTradedQty;
	}

	public long getAvgTradedPrice() {
		return avgTradedPrice;
	}

	public void setAvgTradedPrice(long avgTradedPrice) {
		this.avgTradedPrice = avgTradedPrice;
	}

	public long getVolumeTradedToday() {
		return volumeTradedToday;
	}

	public void setVolumeTradedToday(long volumeTradedToday) {
		this.volumeTradedToday = volumeTradedToday;
	}

	public double getTotalBuyQty() {
		return totalBuyQty;
	}

	public void setTotalBuyQty(double totalBuyQty) {
		this.totalBuyQty = totalBuyQty;
	}

	public double getTotalSellQty() {
		return totalSellQty;
	}

	public void setTotalSellQty(double totalSellQty) {
		this.totalSellQty = totalSellQty;
	}

	public long getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(long openPrice) {
		this.openPrice = openPrice;
	}

	public long getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(long highPrice) {
		this.highPrice = highPrice;
	}

	public long getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(long lowPrice) {
		this.lowPrice = lowPrice;
	}

	public long getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(long closePrice) {
		this.closePrice = closePrice;
	}
}
