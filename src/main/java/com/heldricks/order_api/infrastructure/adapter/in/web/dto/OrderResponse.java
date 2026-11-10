package com.heldricks.order_api.infrastructure.adapter.in.web.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.heldricks.order_api.domain.model.Order;

public record OrderResponse(

        UUID id,
        String customerId,
        BigDecimal totalAmount,
        String status,
        Instant createdAt,
        List<OrderItemResponse> items

) {

    public record OrderItemResponse(
            String productId,
            int quantity,
            BigDecimal unitPrice,
            BigDecimal subtotal
    ) {
    }

    public static OrderResponse from(Order order) {

        List<OrderItemResponse> items =
                order.getItems().stream()
                        .map(item -> new OrderItemResponse(
                                item.productId(),
                                item.quantity(),
                                item.unitPrice(),
                                item.getSubtotal()
                        ))
                        .toList();

        return new OrderResponse(
                order.getId(),
                order.getCustomerId(),
                order.getTotalAmount(),
                order.getStatus().name(),
                order.getCreatedAt(),
                items
        );
    }
}