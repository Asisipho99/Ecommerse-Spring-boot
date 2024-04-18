package com.asisipho.securedEnd.service;

import com.asisipho.securedEnd.entities.Product;
import com.asisipho.securedEnd.model.response.ProductResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    public ResponseEntity<Product> save(Product product);
    public List<ProductResponse> getAllProducts();
}
