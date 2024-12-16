package com.trade.tracker.smartapi.orderupdate;

import com.trade.tracker.smartapi.smartstream.models.SmartStreamError;

public interface OrderUpdateListner {
    void onConnected();
    void onDisconnected();
    void onError(SmartStreamError error);
    void onPong();

    void onOrderUpdate(String data);
}
