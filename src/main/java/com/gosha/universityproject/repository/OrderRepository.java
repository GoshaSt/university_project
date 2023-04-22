package com.gosha.universityproject.repository;

import com.gosha.universityproject.entity.Customer;
import com.gosha.universityproject.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByCustomerOrderByOrderIdDesc(Customer customer);

    List<Order> findAllByOrderByOrderIdDesc();
}
