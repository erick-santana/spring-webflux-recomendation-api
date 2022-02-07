package com.project.reactiveprogramming.repository;

import com.project.reactiveprogramming.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Repository
@Slf4j
public class ProductRepository {

    private final WebClient webclient;

    public ProductRepository(@Qualifier("api") WebClient webclient) {
        this.webclient = webclient;
    }

    public Flux<Product> findProducts() {
        log.info("Buscando produtos no products-api");

        return webclient.get()
                .uri("/product")
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(Product.class));
    }
}
