package com.project.reactiveprogramming.repository.impl;

import com.project.reactiveprogramming.model.Address;
import com.project.reactiveprogramming.model.ShippingResponse;
import com.project.reactiveprogramming.model.Product;
import com.project.reactiveprogramming.service.shipping.ShippingAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ShippingRepository {

    public static final String PARTNER_NAME = "PartnerOne";

    private final ShippingAdapter shippingAdapter;

    public Flux<ShippingResponse> getShipping(List<Product> products, Address address) {
        log.info("Calculando valor do frete com o parceiro [{}] para a cidade [{}]", PARTNER_NAME, address.getCustomerId());

        return Flux.just(products.stream()
                .map(shippingAdapter::toShippingResponse)
                .collect(Collectors.toList())).flatMap(Flux::fromIterable);
    }
}
