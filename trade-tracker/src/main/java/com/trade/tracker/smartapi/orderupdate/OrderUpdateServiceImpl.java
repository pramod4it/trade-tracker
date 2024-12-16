package com.trade.tracker.smartapi.orderupdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trade.tracker.smartapi.smartstream.models.SmartStreamError;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderUpdateServiceImpl implements OrderUpdateListner{
	private static final Logger log = LoggerFactory.getLogger(OrderUpdateServiceImpl.class);

    @Override
    public void onConnected() {
        // TODO Auto-generated method stub
    }

    @Override
    public void onDisconnected() {
        // TODO Auto-generated method stub
    }

    @Override
    public void onError(SmartStreamError error) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onPong() {

    }

    @Override
    public void onOrderUpdate(String data) {
        log.info("order data {} ",data);
    }
}
