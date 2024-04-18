package com.asisipho.securedEnd.controller;

import com.asisipho.securedEnd.entities.Order;
import com.asisipho.securedEnd.entities.Product;
import com.asisipho.securedEnd.model.request.OrderRequest;
import com.asisipho.securedEnd.model.response.OrderResponse;
import com.asisipho.securedEnd.serviceImpl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    OrderServiceImpl orderService;
    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest orderRequest){
        return orderService.placeOrder(orderRequest);
    }
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrdersByUserId(@RequestParam Long id) {
        return orderService.getOrdersByUserId(id);
    }
}
