package com.micropos.orders.rest;

import com.micropos.orders.biz.OrderItemService;
import com.micropos.orders.biz.OrderService;
import com.micropos.orders.model.Order;
import com.micropos.orders.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    private OrderService orderService;
    private OrderItemService orderItemService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    @Autowired
    public void setOrderItemService(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> listOrders() {
        return new ResponseEntity<>(
                orderService.orders(),
                HttpStatus.OK
        );
    }

    @GetMapping("/orderItems")
    public ResponseEntity<List<OrderItem>> listOrderItems() {
        return new ResponseEntity<>(
                orderItemService.orderItems(),
                HttpStatus.OK
        );
    }

    @PostMapping("/createOrder")
    public String createOrder() {
        return "create order!!!!!!";
    }
}
