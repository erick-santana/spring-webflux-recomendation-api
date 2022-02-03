package com.project.reactiveprogramming.service;

import com.project.reactiveprogramming.model.Customer;
import com.project.reactiveprogramming.repository.impl.CustomerRepository;
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
        return customerRepository.findCustomers().collectList()
                .flatMap(customers -> {
                    for (Customer customer : customers) {
                        if (customer.getId().equals(customerId)) {
                            return Mono.just(customer);
                        }
                    }
                    return Mono.empty();
                });
    }
}
