package com.gosha.universityproject.service;

import com.gosha.universityproject.model.dto.OrderDto;
import com.gosha.universityproject.model.request.OrderRequest;

import java.util.List;

public interface OrderService {

    OrderDto create(OrderRequest orderRequest);

    OrderDto confirmOrder(Long id);

    OrderDto deliveredOrder(Long id);

    OrderDto canceledOrder(Long id);

    OrderDto getOrder(Long id);

    List<OrderDto> getCustomerOrder(Long id);

    List<OrderDto> getOrderList();

}
