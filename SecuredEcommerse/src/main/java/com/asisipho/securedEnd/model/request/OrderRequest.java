package com.asisipho.securedEnd.model.request;

import com.asisipho.securedEnd.entities.Product;
import com.asisipho.securedEnd.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private Long id;
    private Long userId;
    private List<Product> products;
    private double totalAmount;
}
