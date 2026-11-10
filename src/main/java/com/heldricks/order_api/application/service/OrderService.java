package com.heldricks.order_api.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heldricks.order_api.application.port.in.CreateOrderUseCase;
import com.heldricks.order_api.application.port.in.GetOrderUseCase;
import com.heldricks.order_api.application.port.out.OrderPersistencePort;
import com.heldricks.order_api.domain.exception.OrderNotFoundException;
import com.heldricks.order_api.domain.model.Order;
import com.heldricks.order_api.domain.model.OrderItem;

@Service
public class OrderService implements CreateOrderUseCase, GetOrderUseCase {

	private final OrderPersistencePort persistencePort;

	public OrderService(
			OrderPersistencePort persistencePort) {
		this.persistencePort = persistencePort;
	}

	@Override
	@Transactional
	public Order createOrder(
			String customerId,
			List<OrderItem> items) {

		Order order = Order.create(customerId, items);

		return persistencePort.save(order);
	}

	@Override
	@Transactional(readOnly = true)
	public Order getOrder(UUID orderId) {

		return persistencePort.findById(orderId)
				.orElseThrow(() -> new OrderNotFoundException(
						"Order not found: " + orderId));
	}

}