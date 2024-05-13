package com.micropos.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
@Getter
@Setter
@AllArgsConstructor
public class OrderDTO {
    private String uid;
    private double amount;
    private List<OrderItemDTO> items;
    private String status;
}
