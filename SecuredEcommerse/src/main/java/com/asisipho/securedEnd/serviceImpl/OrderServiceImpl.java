package com.asisipho.securedEnd.serviceImpl;

import com.asisipho.securedEnd.entities.Order;
import com.asisipho.securedEnd.entities.OrderItem;
import com.asisipho.securedEnd.entities.Product;
import com.asisipho.securedEnd.mapper.OrderMapper;
import com.asisipho.securedEnd.model.request.OrderRequest;
import com.asisipho.securedEnd.model.response.OrderResponse;
import com.asisipho.securedEnd.repository.OrderRepository;
import com.asisipho.securedEnd.repository.ProductRepository;
import com.asisipho.securedEnd.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    @Transactional
    public ResponseEntity<Order> placeOrder(OrderRequest orderRequest) {
        Order order = orderMapper.mapToEntity(orderRequest);
        Map<Long, Integer> productQuantities = getProductQuantities(orderRequest);

        try {
            createOrderItems(order, productQuantities);
            calculateTotalAmount(order);
            //order = orderRepository.save(order);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            // Rollback transaction if there's an exception due to insufficient quantity
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orderResponses = orderMapper.ordersToModel(orderRepository.findAll());
        return new ResponseEntity<>(orderResponses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderResponse>> getOrdersByUserId(Long id) {
        List<OrderResponse> orderResponses = orderMapper.ordersToModel(orderRepository.findOrderByUserId(id));
        return new ResponseEntity<>(orderResponses, HttpStatus.OK);
    }
    private Map<Long, Integer> getProductQuantities(OrderRequest orderRequest) {
        return orderRequest.getProducts().stream()
                .collect(Collectors.groupingBy(Product::getId, Collectors.summingInt(Product::getQuantity)));
    }
    private void createOrderItems(Order order, Map<Long, Integer> productQuantities) {
        boolean hasInsufficientQuantity = false; // Flag to track if any product has insufficient quantity
        for (Map.Entry<Long, Integer> entry : productQuantities.entrySet()) {
            Long productId = entry.getKey();
            Integer quantity = entry.getValue();
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

            // Deduct quantity from available stock
            int remainingQuantity = product.getQuantity() - quantity;
            System.out.println("Product ID: " + productId + " Quantity:" + quantity);
            if (remainingQuantity < 0) {
                hasInsufficientQuantity = true; // Set flag to true if any product has insufficient quantity
                System.out.println("Insufficient quantity for product with ID: " + productId);
                break; // Exit loop if any product has insufficient quantity
            }

            // Update the remaining quantity of the product
            product.setQuantity(remainingQuantity);

            // Create order item only if quantity is sufficient
            //OrderItem orderItem = new OrderItem(order, product, quantity);
            //order.getItems().add(orderItem);
        }

        if (!hasInsufficientQuantity) {
            // If all quantities are sufficient, calculate total amount and save the order
            calculateTotalAmount(order);
            orderRepository.save(order);
        } else {
            // Throw exception if any product has insufficient quantity
            throw new RuntimeException("One or more products have insufficient quantity.");
        }
    }

    private void calculateTotalAmount(Order order) {
        double totalAmount = order.getItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        order.setTotalAmount(totalAmount);
    }
}
