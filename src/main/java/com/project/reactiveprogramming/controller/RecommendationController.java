package com.project.reactiveprogramming.controller;

import com.project.reactiveprogramming.controller.request.RecommendationRequest;
import com.project.reactiveprogramming.controller.response.RecommendationResponse;
import com.project.reactiveprogramming.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequestMapping("/recommendation")
@RequiredArgsConstructor
@RestController
@Slf4j
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping("")
    public Flux<RecommendationResponse> findRecommendations(@RequestBody RecommendationRequest recommendationRequest) {
        return recommendationService.findRecommendations(recommendationRequest);
    }
}
