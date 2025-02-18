package com.fmattaperdomo.ecommerce_arka.services;

import com.fmattaperdomo.ecommerce_arka.entities.User;
import com.fmattaperdomo.ecommerce_arka.dtos.AddressDto;

import java.util.List;

public interface AddressService {
    AddressDto createAddress(AddressDto addressDTO, User user);

    List<AddressDto> getAddresses();

    AddressDto getAddressesById(Long addressId);

    List<AddressDto> getUserAddresses(User user);

    AddressDto updateAddress(Long addressId, AddressDto addressDto);

    String deleteAddress(Long addressId);
}

