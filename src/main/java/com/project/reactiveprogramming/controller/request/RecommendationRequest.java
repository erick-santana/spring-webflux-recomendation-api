package com.project.reactiveprogramming.controller.request;

import com.project.reactiveprogramming.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class RecommendationRequest {

    @NotNull(message = "O campo 'customerId' deve ser preenchido")
    private String customerId;
    @NotNull(message = "O campo 'cartId' deve ser preenchido")
    private String cartId;
    @NotNull(message = "O campo 'products' deve ser preenchido")
    @Valid
    private List<Product> products;
}
