package com.heldricks.order_api.domain.model;

import java.math.BigDecimal;

public record OrderItem(
        String productId,
        int quantity,
        BigDecimal unitPrice
) {

    public OrderItem {
        if (productId == null || productId.isBlank()) {
            throw new IllegalArgumentException(
                    "Product ID is required"
            );
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero"
            );
        }

        if (unitPrice == null ||
                unitPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Unit price must be greater than zero"
            );
        }
    }

    public BigDecimal getSubtotal() {
        return unitPrice.multiply(
                BigDecimal.valueOf(quantity)
        );
    }
}