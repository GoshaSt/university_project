package com.gosha.universityproject.controller;

import com.gosha.universityproject.model.dto.CustomerDto;
import com.gosha.universityproject.security.JwtRequestModel;
import com.gosha.universityproject.security.JwtResponseModel;
import com.gosha.universityproject.security.TokenManager;
import com.gosha.universityproject.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {
    @Autowired
    private CustomerServiceImpl userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;

    @PostMapping("/login")
    public ResponseEntity createToken(@RequestBody JwtRequestModel
                                              request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new
                            UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword())
            );
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final CustomerDto customerDto = userDetailsService.findByUsername(request.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        String role = customerDto.getRoles().get(0);
        return ResponseEntity.ok(new JwtResponseModel(jwtToken, userDetails.getUsername(), customerDto.getEmail(), role));
    }
}
