package com.heldricks.order_api.infrastructure.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class HealthController {

    @GetMapping ("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }
}