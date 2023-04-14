package com.gosha.universityproject.service.impl;

import com.gosha.universityproject.entity.ShippingAddress;
import com.gosha.universityproject.model.dto.ShippingAddressDto;
import com.gosha.universityproject.model.request.ShippingAddressRequest;
import com.gosha.universityproject.repository.ShippingAddressRepository;
import com.gosha.universityproject.service.ShippingAddressService;
import com.gosha.universityproject.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShippingAddressServiceImpl implements ShippingAddressService {

    private final ShippingAddressRepository shippingAddressRepository;

    @Override
    public ShippingAddressDto create(ShippingAddressRequest shippingAddressRequest) {
        ShippingAddress shippingAddress = ModelMapperUtil.modelMapper().map(shippingAddressRequest, ShippingAddress.class);
        shippingAddressRepository.save(shippingAddress);
        ShippingAddressDto shippingAddressDto = ModelMapperUtil.modelMapper().map(shippingAddress, ShippingAddressDto.class);
        System.out.println(shippingAddressDto);
        return shippingAddressDto;
    }
}
