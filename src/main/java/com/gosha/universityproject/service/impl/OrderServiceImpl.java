package com.gosha.universityproject.service.impl;

import com.gosha.universityproject.entity.*;
import com.gosha.universityproject.model.dto.OrderDto;
import com.gosha.universityproject.model.dto.OrderItemDto;
import com.gosha.universityproject.model.dto.ShippingAddressDto;
import com.gosha.universityproject.model.request.OrderRequest;
import com.gosha.universityproject.model.request.ShippingAddressRequest;
import com.gosha.universityproject.repository.OrderRepository;
import com.gosha.universityproject.service.*;
import com.gosha.universityproject.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemService orderItemService;

    private final ProductService productService;

    private final ShippingAddressService shippingAddressService;

    private final CustomerService customerService;

    @Override
    public OrderDto create(OrderRequest orderRequest) {
        ModelMapper modelMapper = ModelMapperUtil.modelMapper();

        AtomicReference<Double> totalSum = new AtomicReference<>(0d);

        Order order = modelMapper.map(orderRequest, Order.class);
        Customer customer = customerService.findById(orderRequest.getCustomer().getCustomerId());
        order.setCustomer(customer);

        orderRepository.save(order);

        List<OrderItemDto> orderItemDtos = orderRequest.getOrderItemRequestList().stream()
                .map(orderItemRequest -> {
                    OrderItem orderItem = modelMapper.map(orderItemRequest, OrderItem.class);
                    orderItem.setOrder(order);
                    Product product = modelMapper.map(productService.findByProductName(orderItemRequest.getProductRequest().getProductName()), Product.class);
                    orderItem.setProduct(product);

                    orderItemService.create(orderItem);
                    totalSum.updateAndGet(v -> v + product.getPrice() * orderItem.getQuantity());
                    return modelMapper.map(orderItem, OrderItemDto.class);
                })
                .collect(Collectors.toList());

        OrderDto orderDto = modelMapper.map(order, OrderDto.class);
        orderDto.setOrderItemDtoList(orderItemDtos);

        ShippingAddress shippingAddress = modelMapper.map(orderRequest.getShippingAddressRequest(), ShippingAddress.class);
        shippingAddress.setOrder(order);
        ShippingAddressDto shippingAddressDto = shippingAddressService.create(modelMapper.map(shippingAddress, ShippingAddressRequest.class));
        orderDto.setShippingAddressDto(shippingAddressDto);
        orderDto.setTotal(totalSum);

        return orderDto;
    }
}
