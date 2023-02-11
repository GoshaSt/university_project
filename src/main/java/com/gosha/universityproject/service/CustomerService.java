package com.gosha.universityproject.service;

import com.gosha.universityproject.entity.Customer;
import com.gosha.universityproject.model.dto.CustomerDto;
import com.gosha.universityproject.model.request.CustomerRequest;

public interface CustomerService {

    CustomerDto create(CustomerRequest customerRequest);

    Customer findById(Long id);

}
