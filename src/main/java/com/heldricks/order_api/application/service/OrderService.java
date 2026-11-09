package com.heldricks.order_api.application.service;

import com.heldricks.order_api.application.port.in.CreateOrderUseCase;
import com.heldricks.order_api.application.port.in.GetOrderUseCase;
import com.heldricks.order_api.application.port.out.OrderPersistencePort;
import com.heldricks.order_api.domain.model.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService implements CreateOrderUseCase, GetOrderUseCase {
	//private final OrderPersistencePort orderPersistencePort;

	public OrderService() {
		
	}

	@Override
	public Order createOrder(Order order) {
		return null;
	}

	@Override
	public Optional<Order> getOrder(UUID orderId) {
		return null;
	}
}