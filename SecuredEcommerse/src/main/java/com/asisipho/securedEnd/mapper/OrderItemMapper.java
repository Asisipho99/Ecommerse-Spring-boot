package com.asisipho.securedEnd.mapper;

import com.asisipho.securedEnd.entities.OrderItem;
import com.asisipho.securedEnd.model.response.OrderItemResponse;
import com.asisipho.securedEnd.model.response.OrderResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderItemMapper {
    @Autowired
    private ModelMapper modelMapper;
    public OrderItemResponse orderItemToModel(OrderItem orderItem) {
        return modelMapper.map(orderItem, OrderItemResponse.class);
    }
    public List<OrderItemResponse> orderItemsToModel(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(this::orderItemToModel)
                .collect(Collectors.toList());
    }
}
