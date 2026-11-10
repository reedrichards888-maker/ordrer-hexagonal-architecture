package com.heldricks.order_api.infrastructure.adapter.out.persistence.mapper;

import com.heldricks.order_api.domain.model.Order;
import com.heldricks.order_api.domain.model.OrderItem;
import com.heldricks.order_api.domain.model.OrderStatus;

import com.heldricks.order_api.infrastructure.adapter.out.persistence.entity.OrderJpaEntity;
import com.heldricks.order_api.infrastructure.adapter.out.persistence.entity.OrderItemJpaEntity;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    public OrderJpaEntity toEntity(Order order) {

        OrderJpaEntity entity = new OrderJpaEntity(
                order.getId(),
                order.getCustomerId(),
                order.getTotalAmount(),
                order.getStatus().name(),
                order.getCreatedAt()
        );

        order.getItems().forEach(item -> {
            OrderItemJpaEntity itemEntity =
                    new OrderItemJpaEntity(
                            item.productId(),
                            item.quantity(),
                            item.unitPrice()
                    );

            entity.addItem(itemEntity);
        });

        return entity;
    }

    public Order toDomain(OrderJpaEntity entity) {

        List<OrderItem> items = entity.getItems()
                .stream()
                .map(item -> new OrderItem(
                        item.getProductId(),
                        item.getQuantity(),
                        item.getUnitPrice()
                ))
                .toList();

        return Order.restore(
                entity.getId(),
                entity.getCustomerId(),
                items,
                entity.getTotalAmount(),
                OrderStatus.valueOf(entity.getStatus()),
                entity.getCreatedAt()
        );
    }
}