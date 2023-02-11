package com.gosha.universityproject.controller;


import com.gosha.universityproject.model.dto.OrderDto;
import com.gosha.universityproject.model.request.OrderRequest;
import com.gosha.universityproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createCustomer(
            @RequestBody OrderRequest orderRequest){
        return ResponseEntity.ok(orderService.create(orderRequest));
    }
}
