package com.gosha.universityproject.controller;

import com.gosha.universityproject.model.dto.CustomerDto;
import com.gosha.universityproject.model.request.CustomerRequest;
import com.gosha.universityproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(
            @RequestBody CustomerRequest customerRequest){
        return ResponseEntity.ok(customerService.create(customerRequest));
    }
}
