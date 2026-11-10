package com.heldricks.order_api.domain.exception;

public class OrderNotFoundException
        extends RuntimeException {

    public OrderNotFoundException(String message) {
        super(message);
    }
}