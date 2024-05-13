package com.micropos.orders.db;

import com.micropos.orders.jpa.OrderItemRepository;
import com.micropos.orders.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderItemDBImp implements OrderItemDB {
    private OrderItemRepository orderItemRepository;
    @Autowired
    public void setOrderItemRepository(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }
    @Override
    public List<OrderItem> getOrderItems() {
        return orderItemRepository.findAll();
    }
}
