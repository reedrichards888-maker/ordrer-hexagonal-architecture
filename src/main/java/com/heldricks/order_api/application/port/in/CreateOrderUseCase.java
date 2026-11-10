package com.heldricks.order_api.application.port.in;

import java.util.List;

import com.heldricks.order_api.domain.model.Order;
import com.heldricks.order_api.domain.model.OrderItem;

public interface CreateOrderUseCase {
	Order createOrder(String customerId, List<OrderItem> items);
}