package com.heldricks.order_api.infrastructure.adapter.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heldricks.order_api.infrastructure.adapter.out.persistence.entity.OrderJpaEntity;

public interface SpringDataOrderRepository extends JpaRepository<OrderJpaEntity, UUID> {

}
