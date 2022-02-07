package com.project.reactiveprogramming.service;

import com.project.reactiveprogramming.model.Customer;
import com.project.reactiveprogramming.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Mono<Customer> findCustomer(String customerId) {
        log.info("Buscando customer com o id [{}]", customerId);

        return customerRepository.findCustomers().collectList()
                .flatMap(customers -> Mono.just(customers.stream()
                        .filter(customer -> customer.getId().equals(customerId))
                        .findFirst().orElseThrow()));
    }
}
