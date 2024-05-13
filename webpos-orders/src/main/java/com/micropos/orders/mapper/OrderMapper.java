package com.micropos.orders.mapper;

import com.micropos.api.dto.OrderDTO;
import com.micropos.orders.model.Order;

import java.time.LocalDateTime;

public class OrderMapper {
    public static Order mapDtoToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setUid(orderDTO.getUid());
        order.setAmount(orderDTO.getAmount());
        order.setStatus(orderDTO.getStatus());
        order.setOrderTime(LocalDateTime.now());
        return order;
    }
}
