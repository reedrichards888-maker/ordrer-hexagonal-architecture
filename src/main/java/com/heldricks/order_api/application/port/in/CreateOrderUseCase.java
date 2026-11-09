package com.heldricks.order_api.application.port.in;

import com.heldricks.order_api.domain.model.Order;

public interface CreateOrderUseCase {
	Order createOrder(Order order);
}