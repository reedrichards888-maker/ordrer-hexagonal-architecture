package com.heldricks.order_api.infrastructure.adapter.in.web.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record CreateOrderRequest(

        @NotBlank String customerId,

        @NotEmpty List<@Valid OrderItemRequest> items

) {

    public record OrderItemRequest(

            @NotBlank String productId,

            @Positive int quantity,

            @NotNull @DecimalMin(value = "0.01") BigDecimal unitPrice

    ) {
    }
}