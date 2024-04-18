package com.asisipho.securedEnd.mapper;

import com.asisipho.securedEnd.entities.Product;
import com.asisipho.securedEnd.model.response.ProductResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    @Autowired
    private ModelMapper modelMapper;
    public ProductResponse toDto(Product product) {

        return modelMapper.map(product, ProductResponse.class);
    }
    public List<ProductResponse> productsToModel(List<Product> products) {
        return products.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
