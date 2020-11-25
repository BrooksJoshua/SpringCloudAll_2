package com.lee.springcloud.service;


import com.lee.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;


public interface PaymentService {
    public int create(Payment payment);
    public Payment getPaymentById(Long id);
}
