package com.gosha.universityproject.service;

import com.gosha.universityproject.model.dto.ShippingAddressDto;
import com.gosha.universityproject.model.request.ShippingAddressRequest;

public interface ShippingAddressService {

    ShippingAddressDto create(ShippingAddressRequest shippingAddressRequest);

}
