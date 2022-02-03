package com.project.reactiveprogramming.controller.response;

import com.project.reactiveprogramming.model.ShippingResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationResponse {

    private List<ShippingResponse> similarProducts;
}
