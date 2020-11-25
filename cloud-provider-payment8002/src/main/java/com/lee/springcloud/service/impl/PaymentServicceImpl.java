package com.lee.springcloud.service.impl;

import com.lee.springcloud.dao.PaymentDao;
import com.lee.springcloud.entities.Payment;
import com.lee.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServicceImpl implements PaymentService {
    @Resource
    public PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
