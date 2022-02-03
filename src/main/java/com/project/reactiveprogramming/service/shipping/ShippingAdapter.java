package com.project.reactiveprogramming.service.shipping;

import com.project.reactiveprogramming.controller.response.RecommendationResponse;
import com.project.reactiveprogramming.model.ShippingResponse;
import com.project.reactiveprogramming.model.Product;
import com.project.reactiveprogramming.model.ShippingOption;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class ShippingAdapter {

    private static final String SHIPPING_ID = UUID.randomUUID().toString();

    public ShippingResponse toShippingResponse(Product product) {
        return ShippingResponse.builder()
                .product(product)
                .shippingOptions(List.of(ShippingOption.builder()
                        .shippingId(SHIPPING_ID)
                        .value(BigDecimal.valueOf(75))
                        .deadline(LocalDate.now().plusDays(7))
                        .build()))
                .build();
    }

    public Mono<RecommendationResponse> toRecommendationResponse(List<ShippingResponse> list) {
        return Mono.just(RecommendationResponse.builder()
                .similarProducts(list)
                .build());
    }
}
