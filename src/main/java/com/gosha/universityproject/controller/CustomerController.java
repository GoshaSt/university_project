package com.gosha.universityproject.controller;

import com.gosha.universityproject.entity.Customer;
import com.gosha.universityproject.model.dto.CustomerDto;
import com.gosha.universityproject.model.request.CustomerRequest;
import com.gosha.universityproject.repository.CustomerRepository;
import com.gosha.universityproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    private final CustomerRepository customerRepository;

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(
            @RequestBody CustomerRequest customerRequest){
        return ResponseEntity.ok(customerService.create(customerRequest));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(customerRepository.findAll());
    }
}
