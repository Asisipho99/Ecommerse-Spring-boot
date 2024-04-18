package com.asisipho.securedEnd.service;

import com.asisipho.securedEnd.entities.Order;
import com.asisipho.securedEnd.model.request.OrderRequest;
import com.asisipho.securedEnd.model.response.OrderResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    public ResponseEntity<Order> placeOrder(OrderRequest orderRequest);
    public ResponseEntity<List<OrderResponse>> getAllOrders();
    public ResponseEntity<List<OrderResponse>> getOrdersByUserId(Long id);
}
