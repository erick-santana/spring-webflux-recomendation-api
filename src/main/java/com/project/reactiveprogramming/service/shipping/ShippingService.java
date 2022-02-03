package com.project.reactiveprogramming.service.shipping;

import com.project.reactiveprogramming.controller.response.RecommendationResponse;
import com.project.reactiveprogramming.model.Address;
import com.project.reactiveprogramming.model.Product;
import com.project.reactiveprogramming.repository.impl.ShippingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShippingService {

    private final ShippingRepository partnerRepository;
    private final ShippingAdapter shippingAdapter;

    public Mono<RecommendationResponse> addShippingOptions(List<Product> products, Address address) {
        return partnerRepository.getShipping(products, address).collectList()
                .flatMap(shippingAdapter::toRecommendationResponse);
    }
}
