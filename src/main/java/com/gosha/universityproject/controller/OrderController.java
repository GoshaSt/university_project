package com.gosha.universityproject.controller;


import com.gosha.universityproject.model.dto.OrderDto;
import com.gosha.universityproject.model.request.OrderRequest;
import com.gosha.universityproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(
            @RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.create(orderRequest));
    }

    @PostMapping("/confirm/{id}")
    public ResponseEntity<OrderDto> confirmOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.confirmOrder(id));
    }

    @PostMapping("/deliver/{id}")
    public ResponseEntity<OrderDto> deliveredOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.deliveredOrder(id));
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<OrderDto> canceledOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.canceledOrder(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<OrderDto>> getOrder(
            @PathVariable Long id) {
        return ResponseEntity.ok(orderService.getCustomerOrder(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrderList() {
        return ResponseEntity.ok(orderService.getOrderList());
    }

}
