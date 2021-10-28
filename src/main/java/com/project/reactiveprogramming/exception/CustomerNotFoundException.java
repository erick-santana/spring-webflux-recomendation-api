package com.project.reactiveprogramming.exception;

public class CustomerNotFoundException extends BaseException {

    public CustomerNotFoundException() {

        super(500, "not_found", "Customer não encontrado");
    }
}
