package com.gosha.universityproject.service.impl;

import com.gosha.universityproject.entity.Customer;
import com.gosha.universityproject.model.dto.CustomerDto;
import com.gosha.universityproject.model.request.CustomerRequest;
import com.gosha.universityproject.repository.CustomerRepositoty;
import com.gosha.universityproject.service.CustomerService;
import com.gosha.universityproject.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepositoty customerRepositoty;

    @Override
    public CustomerDto create(CustomerRequest customerRequest) {
        Customer customer = ModelMapperUtil.modelMapper().map(customerRequest, Customer.class);
        return ModelMapperUtil.modelMapper().map(customerRepositoty.save(customer), CustomerDto.class);
    }

    @Override
    public Customer findById(Long id) {
        Customer customer = customerRepositoty.findById(id).orElseThrow();
        return ModelMapperUtil.modelMapper().map(customer, Customer.class);
    }
}
