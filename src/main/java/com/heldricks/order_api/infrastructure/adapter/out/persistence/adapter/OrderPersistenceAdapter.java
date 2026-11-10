package com.heldricks.order_api.infrastructure.adapter.out.persistence.adapter;


import com.heldricks.order_api.application.port.out.OrderPersistencePort;
import com.heldricks.order_api.domain.model.Order;

import com.heldricks.order_api.infrastructure.adapter.out.persistence.entity.OrderJpaEntity;
import com.heldricks.order_api.infrastructure.adapter.out.persistence.mapper.OrderMapper;
import com.heldricks.order_api.infrastructure.adapter.out.persistence.repository.SpringDataOrderRepository;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class OrderPersistenceAdapter
        implements OrderPersistencePort {

    private final SpringDataOrderRepository repository;
    private final OrderMapper mapper;

    public OrderPersistenceAdapter(
            SpringDataOrderRepository repository,
            OrderMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Order save(Order order) {

        OrderJpaEntity entity = mapper.toEntity(order);

        OrderJpaEntity savedEntity =
                repository.save(entity);

        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Order> findById(UUID orderId) {

        return repository.findById(orderId)
                .map(mapper::toDomain);
    }
}