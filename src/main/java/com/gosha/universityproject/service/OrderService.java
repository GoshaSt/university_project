package com.gosha.universityproject.service;

import com.gosha.universityproject.model.dto.OrderDto;
import com.gosha.universityproject.model.request.OrderRequest;

public interface OrderService {

    OrderDto create(OrderRequest orderRequest);

    OrderDto getOrder(Long id);

}
