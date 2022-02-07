package com.project.reactiveprogramming.service;

import com.project.reactiveprogramming.model.Address;
import com.project.reactiveprogramming.repository.ShippingRepository;
import com.project.reactiveprogramming.service.shipping.ShippingAdapter;
import com.project.reactiveprogramming.service.shipping.ShippingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.List;

import static com.project.reactiveprogramming.factory.Factory.buildPartnerResponse;
import static com.project.reactiveprogramming.factory.Factory.buildProduct;
import static com.project.reactiveprogramming.factory.Factory.buildRecommendationResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShippingServiceTest {

    @Mock
    ShippingRepository partnerRepository;

    @Mock
    ShippingAdapter shippingAdapter;

    @InjectMocks
    ShippingService shippingService;

    @Test
    void addShippingOptions() {
        var product = buildProduct();
        var address = new Address();
        when(partnerRepository.getShipping(anyList(), any(Address.class))).thenReturn(Flux.just(buildPartnerResponse()));
        when(shippingAdapter.toRecommendationResponse(anyList())).thenReturn(Mono.just(buildRecommendationResponse()));

        StepVerifier.create(shippingService.addShippingOptions(List.of(product), address))
                .assertNext(recommendationResponse -> {
                    assertThat(recommendationResponse.getSimilarProducts()
                            .get(0).getProduct().getShippingOptions()
                            .get(0).getValue()).isEqualTo(BigDecimal.valueOf(75));
                }).verifyComplete();
    }
}