package com.gosha.universityproject.model.request;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    private CustomerOrderRequest customer;

    private List<OrderItemRequest> orderItemRequestList;

    private ShippingAddressRequest shippingAddressRequest;

}