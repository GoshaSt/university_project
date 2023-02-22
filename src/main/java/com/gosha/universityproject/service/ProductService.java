package com.gosha.universityproject.service;

import java.util.List;
import com.gosha.universityproject.model.dto.ProductDto;
import com.gosha.universityproject.model.request.ProductRequest;

public interface ProductService {

    ProductDto create(ProductRequest productRequest);

    ProductDto findByProductName(String productName);

    List<ProductDto> getAll();

}
