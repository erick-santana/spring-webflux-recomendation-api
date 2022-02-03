package com.project.reactiveprogramming.repository;

import com.project.reactiveprogramming.model.Address;
import com.project.reactiveprogramming.model.ShippingResponse;
import com.project.reactiveprogramming.model.Product;
import reactor.core.publisher.Flux;

import java.util.List;

public interface PartnerRepository {

    Flux<ShippingResponse> getShipping(List<Product> products, Address address);
}
