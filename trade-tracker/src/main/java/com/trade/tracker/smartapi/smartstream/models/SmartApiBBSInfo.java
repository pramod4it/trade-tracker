package com.trade.tracker.smartapi.smartstream.models;

import lombok.ToString;

@ToString
public class SmartApiBBSInfo {
	public static final int BYTES = (2 * Short.BYTES) + (2 * Long.BYTES);

	// siBbBuySellFlag = 1 buy
	// siBbBuySellFlag = 0 sell
	private short buySellFlag = -1;
	private long quantity = -1;
	private long price = -1;
	private short numberOfOrders = -1;

	public SmartApiBBSInfo(short buySellFlag, long quantity, long price, short numberOfOrders) {
		super();
		this.buySellFlag = buySellFlag;
		this.quantity = quantity;
		this.price = price;
		this.numberOfOrders = numberOfOrders;
	}

	public short getBuySellFlag() {
		return buySellFlag;
	}

	public void setBuySellFlag(short buySellFlag) {
		this.buySellFlag = buySellFlag;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public short getNumberOfOrders() {
		return numberOfOrders;
	}

	public void setNumberOfOrders(short numberOfOrders) {
		this.numberOfOrders = numberOfOrders;
	}

	public static int getBytes() {
		return BYTES;
	}
}