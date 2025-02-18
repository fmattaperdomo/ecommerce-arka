package com.fmattaperdomo.ecommerce_arka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fmattaperdomo.ecommerce_arka.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}