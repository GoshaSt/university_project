package com.gosha.universityproject.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDto {

    private String firstName;

    private String lastName;

    private String email;

    private List<String> roles;

}
