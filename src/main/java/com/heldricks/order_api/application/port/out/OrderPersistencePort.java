package com.heldricks.order_api.application.port.out;

import com.heldricks.order_api.domain.model.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderPersistencePort {
    Order save(Order order);
    Optional<Order> findById(UUID orderId);
}