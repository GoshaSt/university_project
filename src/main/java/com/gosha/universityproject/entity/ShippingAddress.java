package com.gosha.universityproject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "shipping_addresses")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_address_id")
    private Long shippingAddressId;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
    private Order order;

    @Column(name = "address", nullable = false)
    private String address;

}