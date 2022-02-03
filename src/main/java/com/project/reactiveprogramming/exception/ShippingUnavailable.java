package com.project.reactiveprogramming.exception;

public class ShippingUnavailable extends BaseException {

    public ShippingUnavailable() {

        super(500, "unavailable", "Cálculo de envio não está disponível");
    }
}
