package com.gosha.universityproject.model.request;

import lombok.Data;

@Data
public class OrderItemRequest {

    private ProductRequest productRequest;

    private Integer quantity;

}
