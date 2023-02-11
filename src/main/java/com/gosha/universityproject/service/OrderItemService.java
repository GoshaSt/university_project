package com.gosha.universityproject.service;

import com.gosha.universityproject.entity.OrderItem;
import com.gosha.universityproject.model.dto.OrderItemDto;

public interface OrderItemService {

    OrderItemDto create(OrderItem orderItem);

}
