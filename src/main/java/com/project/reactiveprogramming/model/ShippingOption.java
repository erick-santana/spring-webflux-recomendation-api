package com.project.reactiveprogramming.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShippingOption {

    private String shippingId;
    private BigDecimal value;
    private LocalDate deadline;
}
