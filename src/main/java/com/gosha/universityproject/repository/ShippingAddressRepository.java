package com.gosha.universityproject.repository;

import com.gosha.universityproject.entity.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {

    ShippingAddress findByOrder_OrderId(Long id);
}
