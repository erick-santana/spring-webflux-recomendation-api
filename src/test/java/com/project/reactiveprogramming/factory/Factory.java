package com.project.reactiveprogramming.factory;

import com.project.reactiveprogramming.controller.response.RecommendationResponse;
import com.project.reactiveprogramming.model.Address;
import com.project.reactiveprogramming.model.Customer;
import com.project.reactiveprogramming.model.ShippingOption;
import com.project.reactiveprogramming.model.ShippingResponse;
import com.project.reactiveprogramming.model.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Factory {

    private static final String SHIPPING_ID = UUID.randomUUID().toString();

    public static RecommendationResponse buildRecommendationResponse() {
        return RecommendationResponse.builder()
                .similarProducts(List.of(ShippingResponse.builder()
                        .product(buildProduct())
                        .build()))
                .build();
    }

    public static Product buildProduct() {
        return Product.builder()
                .id("1")
                .name("AnyProduct")
                .category("AnyCategory")
                .value(BigDecimal.valueOf(900))
                .shippingOptions(List.of(buildShippingOption()))
                .build();
    }

    public static ShippingResponse buildPartnerResponse() {
        return ShippingResponse.builder()
                .product(buildProduct())
                .shippingOptions(List.of(buildShippingOption()))
                .build();
    }

    public static ShippingOption buildShippingOption() {
        return ShippingOption.builder()
                .shippingId(SHIPPING_ID)
                .value(BigDecimal.valueOf(75))
                .deadline(LocalDate.now().plusDays(7))
                .build();
    }

    public static Customer buildCustomer() {
        return Customer.builder()
                .id("1")
                .name("AnyName")
                .address(Address.builder()
                        .customerId("1")
                        .build())
                .build();
    }
}
