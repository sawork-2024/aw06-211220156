package com.micropos.orders.db;


import com.micropos.orders.model.OrderItem;

import java.util.List;

public interface OrderItemDB {
    List<OrderItem> getOrderItems();

    void createItem(OrderItem orderItem);
}
