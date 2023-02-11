package com.gosha.universityproject.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Data
public class OrderDto {

    private LocalDate orderDate;

    private CustomerDto customer;

    private List<OrderItemDto> orderItemDtoList;

    private AtomicReference<Double> total;

    private ShippingAddressDto shippingAddressDto;


}
