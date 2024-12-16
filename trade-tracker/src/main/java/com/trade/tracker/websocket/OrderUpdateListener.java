package com.trade.tracker.websocket;

import org.springframework.stereotype.Component;

@Component
public class OrderUpdateListener {

    public void onOrderUpdate(String message) {
        System.out.println("Order update received: " + message);
    }
}
