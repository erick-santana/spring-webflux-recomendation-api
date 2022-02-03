package com.project.reactiveprogramming.controller.request;

import com.project.reactiveprogramming.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationRequest {

    private String customerId;
    private List<Product> products;
}
