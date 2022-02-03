package com.project.reactiveprogramming.factory;

import com.project.reactiveprogramming.controller.response.RecommendationResponse;
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
                .name("MyProduct")
                .category("MyCategory")
                .quantity(3)
                .shippingOptions(List.of(buildShippingOption()))
                .build();
    }

    public static ShippingResponse buildPartnerResponse() {
        return ShippingResponse.builder()
                .product(buildProduct())
                .shippingOptions(List.of(buildShippingOption()))
                .build();
    }

    private static ShippingOption buildShippingOption() {
        return ShippingOption.builder()
                .shippingId(SHIPPING_ID)
                .value(BigDecimal.valueOf(75))
                .deadline(LocalDate.now().plusDays(7))
                .build();
    }
}
