package com.fmattaperdomo.ecommerce_arka.controllers;

import com.fmattaperdomo.ecommerce_arka.entities.User;
import com.fmattaperdomo.ecommerce_arka.dtos.AddressDto;
import com.fmattaperdomo.ecommerce_arka.services.AddressService;
import com.fmattaperdomo.ecommerce_arka.util.AuthUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    AuthUtil authUtil;

    @Autowired
    AddressService addressService;

    @PostMapping("/addresses")
    public ResponseEntity<AddressDto> createAddress(@Valid @RequestBody AddressDto addressDto){
        User user = authUtil.loggedInUser();
        AddressDto savedAddressDto = addressService.createAddress(addressDto, user);
        return new ResponseEntity<>(savedAddressDto, HttpStatus.CREATED);
    }

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDto>> getAddresses(){
        List<AddressDto> addressList = addressService.getAddresses();
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @GetMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable Long addressId){
        AddressDto addressDto = addressService.getAddressesById(addressId);
        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }


    @GetMapping("/users/addresses")
    public ResponseEntity<List<AddressDto>> getUserAddresses(){
        User user = authUtil.loggedInUser();
        List<AddressDto> addressList = addressService.getUserAddresses(user);
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable Long addressId
            , @RequestBody AddressDto addressDto){
        AddressDto updatedAddress = addressService.updateAddress(addressId, addressDto);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<String> updateAddress(@PathVariable Long addressId){
        String status = addressService.deleteAddress(addressId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
