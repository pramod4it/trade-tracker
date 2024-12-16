package com.trade.tracker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.trade.tracker.smartapi.SmartConnect;
import com.trade.tracker.smartapi.models.OrderParams;
import com.trade.tracker.smartapi.models.Order;

@Service
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    private final SmartConnect smartConnect;

    public OrderService(SmartConnect smartConnect) {
        this.smartConnect = smartConnect;
    }

    public Order placeOrder(OrderParams orderParams) {
        try {
            LOGGER.info("Placing order: {}", orderParams);
            Order order = smartConnect.placeOrder(orderParams, "regular");
            LOGGER.info("Order placed successfully: {}", order);
            return order;
        } catch (Exception e) {
            LOGGER.error("Error placing order: {}", e.getMessage(), e);
            throw new RuntimeException("Order placement failed", e);
        }
    }

    public Order modifyOrder(OrderParams orderParams, String orderId) {
        try {
            LOGGER.info("Modifying order with ID {}: {}", orderId, orderParams);
            Order order = smartConnect.modifyOrder(orderId, orderParams, "regular");
            LOGGER.info("Order modified successfully: {}", order);
            return order;
        } catch (Exception e) {
            LOGGER.error("Error modifying order: {}", e.getMessage(), e);
            throw new RuntimeException("Order modification failed", e);
        }
    }

    public boolean cancelOrder(String orderId) {
        try {
            LOGGER.info("Cancelling order with ID: {}", orderId);
            Order order = smartConnect.cancelOrder(orderId, "regular");
            if (null != order) {
                LOGGER.info("Order cancelled successfully.");
                return true;
            } else {
                LOGGER.warn("Order cancellation failed.");
            }
            return false;
        } catch (Exception e) {
            LOGGER.error("Error cancelling order: {}", e.getMessage(), e);
            throw new RuntimeException("Order cancellation failed", e);
        }
    }
}
