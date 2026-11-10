package com.heldricks.order_api.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class Order {

    private final UUID id;
    private final String customerId;
    private final List<OrderItem> items;
    private final BigDecimal totalAmount;
    private final OrderStatus status;
    private final Instant createdAt;

    private Order(
            UUID id,
            String customerId,
            List<OrderItem> items,
            BigDecimal totalAmount,
            OrderStatus status,
            Instant createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.items = List.copyOf(items);
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static Order create(
            String customerId,
            List<OrderItem> items) {

        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException(
                    "Customer ID is required");
        }

        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException(
                    "Order must contain at least one item");
        }

        BigDecimal total = items.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Order(
                UUID.randomUUID(),
                customerId,
                items,
                total,
                OrderStatus.PENDING,
                Instant.now());
    }

    public static Order restore(
            UUID id,
            String customerId,
            List<OrderItem> items,
            BigDecimal totalAmount,
            OrderStatus status,
            Instant createdAt) {
        return new Order(
                id,
                customerId,
                items,
                totalAmount,
                status,
                createdAt);
    }

    public UUID getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}