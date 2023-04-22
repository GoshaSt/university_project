package com.gosha.universityproject.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String token;

    private String username;

    private String email;

    private String role;

}
