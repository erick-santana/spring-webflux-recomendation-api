package com.project.reactiveprogramming.service;

import com.project.reactiveprogramming.model.Customer;
import com.project.reactiveprogramming.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.NoSuchElementException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private static final String CUSTOMER_ID = UUID.randomUUID().toString();

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService;

    @Test
    void testFindCustomerSuccess() {
        when(customerRepository.findCustomers()).thenReturn(Flux.just(buildCustomer()));

        StepVerifier.create(customerService.findCustomer(CUSTOMER_ID))
                .assertNext(customer -> {
                    assertThat(customer.getId()).isEqualTo(CUSTOMER_ID);
                }).verifyComplete();
    }

    @Test
    void testFindCustomerNotFound() {
        when(customerRepository.findCustomers()).thenReturn(Flux.empty());

        StepVerifier.create(customerService.findCustomer(CUSTOMER_ID))
                .expectError(NoSuchElementException.class).verify();
    }

    private Customer buildCustomer() {
        return Customer.builder()
                .id(CUSTOMER_ID)
                .build();
    }
}