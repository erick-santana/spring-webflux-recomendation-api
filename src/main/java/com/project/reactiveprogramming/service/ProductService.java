package com.project.reactiveprogramming.service;

import com.project.reactiveprogramming.model.Product;
import com.project.reactiveprogramming.repository.impl.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public Flux<Product> findSimilarProducts(List<Product> orderedProducts) {
        return productRepository.findProducts()
                .switchIfEmpty(Flux.empty())
                .flatMap(product -> {
                    for (Product p : orderedProducts) {
                        if (p.getCategory().equals(product.getCategory())) {
                            return Flux.just(product);
                        }
                    }
                    return Flux.empty();
                });
    }
}
