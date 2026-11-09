package com.heldricks.order_api.domain.model;

import java.util.List;
import java.util.UUID;

public record Order(UUID id, List<OrderItem> items, OrderStatus status) {
	public Order {
		items = List.copyOf(items);
	}
}