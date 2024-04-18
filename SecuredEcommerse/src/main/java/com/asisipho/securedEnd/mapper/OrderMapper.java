package com.asisipho.securedEnd.mapper;

import com.asisipho.securedEnd.entities.Order;
import com.asisipho.securedEnd.entities.OrderItem;
import com.asisipho.securedEnd.entities.Product;
import com.asisipho.securedEnd.entities.User;
import com.asisipho.securedEnd.model.request.OrderRequest;
import com.asisipho.securedEnd.model.response.OrderResponse;
import com.asisipho.securedEnd.repository.ProductRepository;
import com.asisipho.securedEnd.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public OrderResponse orderToModel(Order order) {
        return modelMapper.map(order, OrderResponse.class);
    }

    public List<OrderResponse> ordersToModel(List<Order> orders) {
        return orders.stream()
                .map(this::orderToModel)
                .collect(Collectors.toList());
    }

    public Order mapToEntity(OrderRequest orderRequest) {
        Order order = new Order();
        order.setTotalAmount(orderRequest.getTotalAmount());

        // Set user
        User user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + orderRequest.getUserId()));
        order.setUser(user);

        // Set products
        List<Long> productIds = orderRequest.getProducts().stream()
                .map(Product::getId)
                .collect(Collectors.toList());

        List<Product> products = productRepository.findAllById(productIds);
        if (products.size() != productIds.size()) {
            throw new RuntimeException("One or more products not found.");
        }

        // Create OrderItem for each product and associate it with the order
        for (Product product : products) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            order.getItems().add(orderItem);
        }
        return order;
    }
}
