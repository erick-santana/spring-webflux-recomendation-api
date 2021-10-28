package com.project.reactiveprogramming.service;

import com.project.reactiveprogramming.exception.CustomerNotFoundException;
import com.project.reactiveprogramming.model.OrderRequest;
import com.project.reactiveprogramming.model.OrderResponse;
import com.project.reactiveprogramming.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final CustomerService customerService;
    private final ProductService productService;
    private final PartnerRepository partnerRepository;

    public Mono<OrderResponse> create(OrderRequest orderRequest) {
        return customerService.findCustomer(orderRequest.getCustomerId())
                .switchIfEmpty(Mono.error(new CustomerNotFoundException()))
                .flatMap(customer -> productService.findSimilarProducts(orderRequest.getProducts()).collectList()
                        .flatMap(similarProducts -> partnerRepository.getShippingValue(orderRequest.getProducts(), customer.getAddress())
                                .flatMap(shipping ->
                                        Mono.just(OrderResponse.builder()
                                                .similarProducts(similarProducts)
                                                .shipping(shipping)
                                                .build()))));
    }
}
