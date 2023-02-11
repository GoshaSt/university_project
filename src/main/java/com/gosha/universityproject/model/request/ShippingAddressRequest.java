package com.gosha.universityproject.model.request;

import com.gosha.universityproject.entity.Order;
import lombok.Data;

@Data
public class ShippingAddressRequest {

    private Order order;

    private String address;

}
