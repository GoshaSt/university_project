package com.gosha.universityproject.service.impl;

import com.gosha.universityproject.entity.Customer;
import com.gosha.universityproject.model.dto.CustomerDto;
import com.gosha.universityproject.model.request.CustomerRequest;
import com.gosha.universityproject.repository.CustomerRepository;
import com.gosha.universityproject.service.CustomerService;
import com.gosha.universityproject.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

    private final CustomerRepository customerRepositoty;

    @Override
    public CustomerDto create(CustomerRequest customerRequest) {
        Customer customer = ModelMapperUtil.modelMapper().map(customerRequest, Customer.class);
        List<String> roleList = new ArrayList<>();
        roleList.add("USER");
        customer.setRoles(roleList);
        return ModelMapperUtil.modelMapper().map(customerRepositoty.save(customer), CustomerDto.class);
    }

    @Override
    public Customer findById(Long id) {
        Customer customer = customerRepositoty.findById(id).orElseThrow();
        return ModelMapperUtil.modelMapper().map(customer, Customer.class);
    }

    @Override
    public CustomerDto findByUsername(String username) {
        Customer customer = customerRepositoty.findByUsername(username).orElseThrow();
        return ModelMapperUtil.modelMapper().map(customer, CustomerDto.class);
    }

    @Override
    public Customer findByUser(String username) {
        Customer customer = customerRepositoty.findByUsername(username).orElseThrow();
        return customer;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Customer customer = customerRepositoty.findByUsername(username).orElseThrow();
        if (customer.getUsername().equals(username)) {
            return new User(customer.getUsername(),
                    passwordEncoder.encode(customer.getPassword()),
                    customer.getAuthorities());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
