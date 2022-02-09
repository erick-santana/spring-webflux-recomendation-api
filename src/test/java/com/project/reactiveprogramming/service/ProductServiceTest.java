package com.project.reactiveprogramming.service;

import com.project.reactiveprogramming.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static com.project.reactiveprogramming.factory.Factory.buildProduct;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    void findSimilarProductsSuccess() {
        var product = buildProduct();
        when(productRepository.findProducts()).thenReturn(Flux.just(product));

        StepVerifier.create(productService.findSimilarProducts(List.of(product)))
                .assertNext(response -> {
                    assertThat(response.getCategory()).isEqualTo("AnyCategory");
                }).verifyComplete();
    }

    @Test
    void findSimilarProductsReturnsEmptyList() {
        var product = buildProduct();
        when(productRepository.findProducts()).thenReturn(Flux.empty());

        StepVerifier.create(productService.findSimilarProducts(List.of(product)))
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    void findSimilarProductsWithoutOrderedProducts() {
        StepVerifier.create(productService.findSimilarProducts(List.of()))
                .expectNextCount(0)
                .verifyComplete();
    }


}