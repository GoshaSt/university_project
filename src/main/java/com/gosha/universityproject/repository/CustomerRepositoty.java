package com.gosha.universityproject.repository;

import com.gosha.universityproject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepositoty extends JpaRepository<Customer, Long> {
}
