package com.heldricks.order_api.application.port.in;

import com.heldricks.order_api.domain.model.Order;

import java.util.UUID;

public interface GetOrderUseCase {
	Order getOrder(UUID orderId);
}