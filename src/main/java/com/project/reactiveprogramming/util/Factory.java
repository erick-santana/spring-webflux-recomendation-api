package com.project.reactiveprogramming.util;

import com.project.reactiveprogramming.model.Shipping;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Factory {

    public static Shipping getShipping() {
        return Shipping.builder()
                .value(BigDecimal.valueOf(75))
                .deadline(LocalDate.now().plusDays(6))
                .build();
    }
}
