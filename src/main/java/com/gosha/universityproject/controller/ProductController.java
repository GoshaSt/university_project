package com.gosha.universityproject.controller;

import com.gosha.universityproject.model.dto.ProductDto;
import com.gosha.universityproject.model.request.ProductRequest;
import com.gosha.universityproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> createCustomer(
            @RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(productService.create(productRequest));
    }
}
