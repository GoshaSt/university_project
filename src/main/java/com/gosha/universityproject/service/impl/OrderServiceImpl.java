package com.gosha.universityproject.service.impl;

import com.gosha.universityproject.entity.*;
import com.gosha.universityproject.model.dto.*;
import com.gosha.universityproject.model.request.OrderRequest;
import com.gosha.universityproject.model.request.ShippingAddressRequest;
import com.gosha.universityproject.repository.OrderItemRepository;
import com.gosha.universityproject.repository.OrderRepository;
import com.gosha.universityproject.repository.ShippingAddressRepository;
import com.gosha.universityproject.service.*;
import com.gosha.universityproject.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    private final OrderItemRepository orderItemRepository;

    private final ShippingAddressRepository shippingAddressRepository;

    @Override
    public OrderDto create(OrderRequest orderRequest) {
        ModelMapper modelMapper = ModelMapperUtil.modelMapper();
        OrderDto orderDto = new OrderDto();
        AtomicReference<Double> totalSum = new AtomicReference<>(0d);

        if (orderRequest.getId() == null) {

            Order order = modelMapper.map(orderRequest, Order.class);
            Customer customer = customerService.findByUser(orderRequest.getCustomer().getCustomerUsername());
            order.setCustomer(customer);

            orderRepository.save(order);

            List<OrderItemDto> orderItemDtos = orderRequest.getOrderItemRequestList().stream().map(orderItemRequest -> {
                OrderItem orderItem = modelMapper.map(orderItemRequest, OrderItem.class);
                orderItem.setOrder(order);
                Product product = modelMapper.map(productService.findByProductName(orderItemRequest.getProductRequest().getProductName()), Product.class);
                orderItem.setProduct(product);

                orderItemService.create(orderItem);
                totalSum.updateAndGet(v -> v + product.getPrice() * orderItem.getQuantity());
                return modelMapper.map(orderItem, OrderItemDto.class);
            }).collect(Collectors.toList());

            orderDto = modelMapper.map(order, OrderDto.class);
            orderDto.setId(order.getOrderId());
            orderDto.setOrderItemDtoList(orderItemDtos);

            ShippingAddress shippingAddress = modelMapper.map(orderRequest.getShippingAddressRequest(), ShippingAddress.class);
            shippingAddress.setOrder(order);
            ShippingAddressDto shippingAddressDto = shippingAddressService.create(modelMapper.map(shippingAddress, ShippingAddressRequest.class));
            orderDto.setShippingAddressDto(shippingAddressDto);
            orderDto.setTotal(totalSum);
            return orderDto;
        } else {
            Order order = orderRepository.findById(orderRequest.getId()).orElseThrow();
            List<OrderItemDto> orderItemDtos = orderRequest.getOrderItemRequestList().stream().map(orderItemRequest -> {
                OrderItem orderItem = modelMapper.map(orderItemRequest, OrderItem.class);
                orderItem.setOrder(order);
                Product product = modelMapper.map(productService.findByProductName(orderItemRequest.getProductRequest().getProductName()), Product.class);
                orderItem.setProduct(product);

                orderItemService.create(orderItem);
                totalSum.updateAndGet(v -> v + product.getPrice() * orderItem.getQuantity());
                return modelMapper.map(orderItem, OrderItemDto.class);
            }).collect(Collectors.toList());

            orderDto = modelMapper.map(order, OrderDto.class);
            orderDto.setId(order.getOrderId());
            orderDto.setOrderItemDtoList(orderItemDtos);
            orderDto.setTotal(totalSum);
            return orderDto;
        }
    }

    @Override
    public OrderDto confirmOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setConfirmed(true);
        orderRepository.save(order);
        return ModelMapperUtil.modelMapper().map(order, OrderDto.class);
    }

    @Override
    public OrderDto deliveredOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setDelivered(true);
        orderRepository.save(order);
        return ModelMapperUtil.modelMapper().map(order, OrderDto.class);
    }

    @Override
    public OrderDto canceledOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setCanceled(true);
        orderRepository.save(order);
        return ModelMapperUtil.modelMapper().map(order, OrderDto.class);
    }

    @Override
    public List<OrderDto> getCustomerOrder(Long id){
        List<Order> orderList = orderRepository.findAllByCustomerOrderByOrderIdDesc(customerService.findById(id));
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : orderList) {
            OrderDto orderDto = getOrder(order.getOrderId());
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }


    @Override
    public OrderDto getOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        AtomicReference<Double> totalSum = new AtomicReference<>(0d);
        OrderDto orderDto = ModelMapperUtil.modelMapper().map(order, OrderDto.class);
        orderDto.setId(order.getOrderId());
        CustomerDto customerDto = ModelMapperUtil.modelMapper().map(order.getCustomer(), CustomerDto.class);
        orderDto.setCustomer(customerDto);
        List<OrderItem> orderItemList = orderItemRepository.findAllByOrder_OrderId(id);
        List<OrderItemDto> orderItemDtoList = new ArrayList<>();
        for (int i = 0; i < orderItemList.size(); i++) {
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setProduct(ModelMapperUtil.modelMapper().map(orderItemList.get(i).getProduct(), ProductDto.class));
            orderItemDto.setQuantity(orderItemList.get(i).getQuantity());
            int finalI = i;
            totalSum.updateAndGet(v -> v + orderItemList.get(finalI).getProduct().getPrice() * orderItemList.get(finalI).getQuantity());
            orderItemDtoList.add(orderItemDto);

        }
        orderDto.setOrderItemDtoList(orderItemDtoList);
        orderDto.setTotal(totalSum);
        orderDto.setShippingAddressDto(ModelMapperUtil.modelMapper().map(shippingAddressRepository.findByOrder_OrderId(id), ShippingAddressDto.class));
        return orderDto;
    }

    @Override
    public List<OrderDto> getOrderList() {
        List<Order> orderList = orderRepository.findAllByOrderByOrderIdDesc();

        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : orderList) {
            OrderDto orderDto = getOrder(order.getOrderId());
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

}
