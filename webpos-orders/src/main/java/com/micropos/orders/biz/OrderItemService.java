package com.micropos.orders.biz;

import com.micropos.orders.model.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> orderItems();

    void createItem(OrderItem orderItem);
}
