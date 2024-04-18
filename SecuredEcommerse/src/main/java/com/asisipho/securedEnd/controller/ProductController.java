package com.asisipho.securedEnd.controller;

import com.asisipho.securedEnd.entities.Product;
import com.asisipho.securedEnd.serviceImpl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return productService.save(product);
    }
}
