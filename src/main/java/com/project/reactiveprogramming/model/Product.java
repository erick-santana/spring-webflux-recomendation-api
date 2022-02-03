package com.project.reactiveprogramming.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String id;
    private String name;
    private String category;
    private String description;
    private int quantity;
    private BigDecimal unitValue;
    private BigDecimal totalAmount;
    private List<ShippingOption> shippingOptions;
}
