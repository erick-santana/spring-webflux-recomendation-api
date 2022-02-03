package com.project.reactiveprogramming.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String customerId;
    private String street;
    private String number;
    private String district;
    private String city;
    private String state;
    private String country;
}
