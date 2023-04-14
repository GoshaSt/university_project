package com.gosha.universityproject.controller;


import com.gosha.universityproject.model.dto.OrderDto;
import com.gosha.universityproject.model.request.OrderRequest;
import com.gosha.universityproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(
            @RequestBody OrderRequest orderRequest){
        return ResponseEntity.ok(orderService.create(orderRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> createOrder(
            @PathVariable Long id){
        System.out.println(orderService.getOrder(id));
        return ResponseEntity.ok(orderService.getOrder(id));
    }

}
