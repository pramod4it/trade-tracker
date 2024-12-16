package com.trade.tracker.smartapi.smartstream.models;

import lombok.ToString;

@ToString
public class BestTwentyData {
    private long quantity = -1;
    private long price = -1;
    private short numberOfOrders = -1;
	public BestTwentyData(long quantity, long price, short numberOfOrders) {
		super();
		this.quantity = quantity;
		this.price = price;
		this.numberOfOrders = numberOfOrders;
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
}
