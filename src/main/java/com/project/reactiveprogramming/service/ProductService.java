package com.project.reactiveprogramming.service;

import com.project.reactiveprogramming.model.Product;
import com.project.reactiveprogramming.repository.ProductRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    @CircuitBreaker(name = "productApi", fallbackMethod = "fallback")
    public Flux<Product> findSimilarProducts(List<Product> orderedProducts) {
        var categories = new ArrayList<>();
        orderedProducts.forEach(product -> categories.add(product.getCategory()));

        if (categories.isEmpty()) {
            return Flux.empty();
        }

        log.info("Buscando produtos similares nas categorias {}", categories);

        return productRepository.findProducts()
                .switchIfEmpty(Flux.empty())
                .flatMap(product -> Flux.just(orderedProducts.stream()
                        .filter(p -> p.getCategory().equals(product.getCategory()))
                        .collect(Collectors.toList()))).flatMap(Flux::fromIterable);
    }

    private Flux<Product> fallback() {
        log.info("Fallback chamado para tratar instabilidade do product-api");

        return Flux.empty();
    }
}
