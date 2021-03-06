package com.project.reactiveprogramming.repository;

import com.project.reactiveprogramming.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Repository
@Slf4j
public class CustomerRepository {

    private final WebClient webclient;

    public CustomerRepository(@Qualifier("api") WebClient webclient) {
        this.webclient = webclient;
    }

    public Flux<Customer> findCustomers() {
        log.info("Buscando customers no customer-api");

        return webclient.get()
                .uri("/customer")
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(Customer.class));
    }
}
