package com.fmattaperdomo.ecommerce_arka.controllers;

import com.fmattaperdomo.ecommerce_arka.dtos.OrderDto;
import com.fmattaperdomo.ecommerce_arka.dtos.OrderRequestDto;
import com.fmattaperdomo.ecommerce_arka.services.OrderService;
import com.fmattaperdomo.ecommerce_arka.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthUtil authUtil;

    @PostMapping("/order/users/payments/{paymentMethod}")
    public ResponseEntity<OrderDto> orderProducts(@PathVariable String paymentMethod, @RequestBody OrderRequestDto orderRequestDto) {
        String emailId = authUtil.loggedInEmail();
        OrderDto order = orderService.placeOrder(
                emailId,
                orderRequestDto.getAddressId(),
                paymentMethod,
                orderRequestDto.getPgName(),
                orderRequestDto.getPgPaymentId(),
                orderRequestDto.getPgStatus(),
                orderRequestDto.getPgResponseMessage()
        );
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
