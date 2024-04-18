package com.asisipho.securedEnd.serviceImpl;

import com.asisipho.securedEnd.entities.Product;
import com.asisipho.securedEnd.mapper.ProductMapper;
import com.asisipho.securedEnd.model.response.ProductResponse;
import com.asisipho.securedEnd.repository.ProductRepository;
import com.asisipho.securedEnd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductMapper productMapper;

    @Override
    public ResponseEntity<Product> save(Product product) {
        Product savedProduct = productRepository.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        return productMapper.productsToModel(productRepository.findAll());
    }
}