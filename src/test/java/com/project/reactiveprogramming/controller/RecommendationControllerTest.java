package com.project.reactiveprogramming.controller;

import com.project.reactiveprogramming.controller.request.RecommendationRequest;
import com.project.reactiveprogramming.exception.CustomerNotFoundException;
import com.project.reactiveprogramming.service.RecommendationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static com.project.reactiveprogramming.factory.Factory.buildRecommendationResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecommendationControllerTest {

    @Mock
    RecommendationService recommendationService;

    @InjectMocks
    RecommendationController recommendationController;

    @Test
    void testFindRecommendationsSuccess() {
        var recommendationRequest = new RecommendationRequest();
        var recommendationResponse = buildRecommendationResponse();
        when(recommendationService.findRecommendations(recommendationRequest))
                .thenReturn(Flux.just(recommendationResponse));

        StepVerifier.create(recommendationController.findRecommendations(recommendationRequest))
                .assertNext(response -> {
                    assertThat(response.getSimilarProducts().get(0).getProduct().getName()).isEqualTo("AnyProduct");
                }).verifyComplete();
    }

    @Test
    void testFindRecommendationsError() {
        var recommendationRequest = new RecommendationRequest();
        when(recommendationService.findRecommendations(recommendationRequest))
                .thenReturn(Flux.error(new CustomerNotFoundException()));

        StepVerifier.create(recommendationController.findRecommendations(recommendationRequest))
                .expectError(CustomerNotFoundException.class)
                .verify();
    }
}