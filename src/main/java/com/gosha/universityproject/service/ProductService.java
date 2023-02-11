package com.gosha.universityproject.service;

import com.gosha.universityproject.model.dto.ProductDto;
import com.gosha.universityproject.model.request.ProductRequest;

public interface ProductService {

    ProductDto create(ProductRequest productRequest);

    ProductDto findByProductName(String productName);

}
