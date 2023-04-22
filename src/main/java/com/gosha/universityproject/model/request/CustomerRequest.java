package com.gosha.universityproject.model.request;

import lombok.Data;

@Data
public class CustomerRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

}
