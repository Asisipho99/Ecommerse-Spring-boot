package com.asisipho.securedEnd.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {
    private Long id;

    private Long orderId;

    private Long productId;

    private int quantity;
}
