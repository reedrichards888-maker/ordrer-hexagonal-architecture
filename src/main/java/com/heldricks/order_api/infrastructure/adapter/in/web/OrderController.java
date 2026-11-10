package com.heldricks.order_api.infrastructure.adapter.in.web;

import com.heldricks.order_api.application.port.in.CreateOrderUseCase;
import com.heldricks.order_api.application.port.in.GetOrderUseCase;
import com.heldricks.order_api.domain.model.OrderItem;

import com.heldricks.order_api.infrastructure.adapter.in.web.dto.CreateOrderRequest;
import com.heldricks.order_api.infrastructure.adapter.in.web.dto.OrderResponse;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;

    public OrderController(
            CreateOrderUseCase createOrderUseCase,
            GetOrderUseCase getOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @Valid @RequestBody CreateOrderRequest request) {

        var items = request.items().stream()
                .map(item -> new OrderItem(
                        item.productId(),
                        item.quantity(),
                        item.unitPrice()))
                .toList();

        var order = createOrderUseCase.createOrder(
                request.customerId(),
                items);

        var response = OrderResponse.from(order);

        return ResponseEntity
                .created(URI.create(
                        "/api/v1/orders/" + order.getId()))
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(
            @PathVariable("id") UUID id) {

        var order = getOrderUseCase.getOrder(id);

        return ResponseEntity.ok(
                OrderResponse.from(order));
    }
}