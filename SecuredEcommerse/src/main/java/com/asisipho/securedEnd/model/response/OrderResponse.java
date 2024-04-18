package com.asisipho.securedEnd.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private Long userId;
    private List<OrderItemResponse> items = new ArrayList<>();
    private double totalAmount;
}
