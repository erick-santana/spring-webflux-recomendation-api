package com.project.reactiveprogramming.controller;

import com.project.reactiveprogramming.model.OrderRequest;
import com.project.reactiveprogramming.model.OrderResponse;
import com.project.reactiveprogramming.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping("/order")
@RequiredArgsConstructor
@RestController
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping("")
    public Mono<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.create(orderRequest);
    }
}
