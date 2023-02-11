package com.gosha.universityproject.service.impl;

import com.gosha.universityproject.entity.OrderItem;
import com.gosha.universityproject.model.dto.OrderItemDto;
import com.gosha.universityproject.repository.OrderItemRepository;
import com.gosha.universityproject.service.OrderItemService;
import com.gosha.universityproject.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderItemDto create(OrderItem orderItem) {
        return ModelMapperUtil.modelMapper().map(orderItemRepository.save(orderItem), OrderItemDto.class);
    }
}
