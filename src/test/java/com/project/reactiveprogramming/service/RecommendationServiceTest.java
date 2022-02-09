package com.project.reactiveprogramming.service;

import com.project.reactiveprogramming.controller.request.RecommendationRequest;
import com.project.reactiveprogramming.exception.CustomerNotFoundException;
import com.project.reactiveprogramming.model.Address;
import com.project.reactiveprogramming.service.shipping.ShippingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static com.project.reactiveprogramming.factory.Factory.buildCustomer;
import static com.project.reactiveprogramming.factory.Factory.buildProduct;
import static com.project.reactiveprogramming.factory.Factory.buildRecommendationResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecommendationServiceTest {

    @Mock
    CustomerService customerService;

    @Mock
    ProductService productService;

    @Mock
    ShippingService shippingService;

    @InjectMocks
    RecommendationService recommendationService;

    @Test
    void findRecommendationsSuccess() {
        var recommendationRequest = RecommendationRequest.builder()
                .customerId("1")
                .products(List.of(buildProduct()))
                .build();

        when(customerService.findCustomer(anyString())).thenReturn(Mono.just(buildCustomer()));
        when(productService.findSimilarProducts(anyList())).thenReturn(Flux.just(buildProduct()));
        when(shippingService.addShippingOptions(anyList(), any(Address.class)))
                .thenReturn(Mono.just(buildRecommendationResponse()));

        StepVerifier.create(recommendationService.findRecommendations(recommendationRequest))
                .assertNext(response -> {
                    assertThat(response.getSimilarProducts().get(0).getProduct().getCategory())
                            .isEqualTo("AnyCategory");
                }).verifyComplete();
    }

    @Test
    void findRecommendationsReturnsEmptyList() {
        var recommendationRequest = RecommendationRequest.builder()
                .customerId("1")
                .products(List.of(buildProduct()))
                .build();

        when(customerService.findCustomer(anyString())).thenReturn(Mono.just(buildCustomer()));
        when(productService.findSimilarProducts(anyList())).thenReturn(Flux.empty());

        StepVerifier.create(recommendationService.findRecommendations(recommendationRequest))
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    void findRecommendationsCustomerException() {
        var recommendationRequest = RecommendationRequest.builder()
                .customerId("1")
                .products(List.of(buildProduct()))
                .build();

        when(customerService.findCustomer(anyString())).thenReturn(Mono.error(new CustomerNotFoundException()));

        StepVerifier.create(recommendationService.findRecommendations(recommendationRequest))
                .expectError(CustomerNotFoundException.class)
                .verify();
    }
}