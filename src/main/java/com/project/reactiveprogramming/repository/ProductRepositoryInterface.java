package com.project.reactiveprogramming.repository;

import com.project.reactiveprogramming.model.Product;
import reactor.core.publisher.Flux;

public interface ProductRepositoryInterface {
    Flux<Product> findProducts();
}
