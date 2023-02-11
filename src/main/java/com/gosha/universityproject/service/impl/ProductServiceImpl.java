package com.gosha.universityproject.service.impl;

import com.gosha.universityproject.entity.Product;
import com.gosha.universityproject.model.dto.ProductDto;
import com.gosha.universityproject.model.request.ProductRequest;
import com.gosha.universityproject.repository.ProductRepository;
import com.gosha.universityproject.service.ProductService;
import com.gosha.universityproject.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDto create(ProductRequest productRequest) {
        Product product = ModelMapperUtil.modelMapper().map(productRequest, Product.class);
        return ModelMapperUtil.modelMapper().map(productRepository.save(product), ProductDto.class);
    }

    @Override
    public ProductDto findByProductName(String productName) {
        return ModelMapperUtil.modelMapper().map(productRepository.findByProductName(productName), ProductDto.class);
    }
}
