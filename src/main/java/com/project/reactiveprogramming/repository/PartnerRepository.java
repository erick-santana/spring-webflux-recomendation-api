package com.project.reactiveprogramming.repository;

import com.project.reactiveprogramming.model.Address;
import com.project.reactiveprogramming.model.Product;
import com.project.reactiveprogramming.model.Shipping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.project.reactiveprogramming.util.Factory.getShipping;

@Repository
@Slf4j
public class PartnerRepository {

    public Mono<Shipping> getShippingValue(List<Product> products, Address address) {
        /* O ideal seria chamar uma outra API para calcular os dados
           do envio baseado nos produtos e no endereço */
        return Mono.just(getShipping());
    }
}
