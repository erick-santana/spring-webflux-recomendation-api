package com.project.reactiveprogramming.service;

import com.project.reactiveprogramming.controller.request.RecommendationRequest;
import com.project.reactiveprogramming.controller.response.RecommendationResponse;
import com.project.reactiveprogramming.exception.CustomerNotFoundException;
import com.project.reactiveprogramming.service.shipping.ShippingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendationService {

    private final CustomerService customerService;
    private final ProductService productService;
    private final ShippingService shippingService;

    public Flux<RecommendationResponse> findRecommendations(RecommendationRequest recommendationRequest) {
        return customerService.findCustomer(recommendationRequest.getCustomerId())
                .onErrorResume(error -> Mono.error(new CustomerNotFoundException()))
                .flatMap(customer -> productService.findSimilarProducts(recommendationRequest.getProducts())
                        .collectList()
                        .flatMap(similarProducts -> {
                            if (similarProducts.isEmpty()) {
                                return Mono.empty();
                            }
                            return shippingService.addShippingOptions(similarProducts, customer.getAddress());
                        })).flatMapMany(Flux::just);
    }
}
