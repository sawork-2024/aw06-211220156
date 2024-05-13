package com.micropos.orders.biz;

import com.micropos.orders.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> orders();

    String createOrder(Order order);

}
