package com.micropos.orders.mapper;

import com.micropos.api.dto.OrderItemDTO;
import com.micropos.orders.model.OrderItem;

import java.time.LocalDateTime;

public class OrderItemMapper {
    public static OrderItem mapDtoToEntity(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(orderItemDTO.getPid());
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setPrice(orderItemDTO.getPrice());
        return orderItem;
    }
}
