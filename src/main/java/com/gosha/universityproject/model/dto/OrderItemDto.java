package com.gosha.universityproject.model.dto;

import lombok.Data;

@Data
public class OrderItemDto {

    private ProductDto product;

    private Integer quantity;

}
