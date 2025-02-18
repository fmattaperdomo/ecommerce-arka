package com.fmattaperdomo.ecommerce_arka.services;

import com.fmattaperdomo.ecommerce_arka.dtos.OrderDto;
import jakarta.transaction.Transactional;

public interface OrderService {
    @Transactional
    OrderDto placeOrder(String emailId, Long addressId, String paymentMethod, String pgName, String pgPaymentId, String pgStatus, String pgResponseMessage);
}
