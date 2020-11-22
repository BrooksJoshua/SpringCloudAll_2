package com.lee.springcloud.controller;

import com.lee.springcloud.entities.Payment;
import com.lee.springcloud.service.PaymentService;
import com.lee.springcloud.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    //private static final LoggerFactory logger=LoggerFactory.getLogger(PaymentController.class);

    @Resource
    PaymentService paymentService;

    @PostMapping("/p/create")
    public CommonResult create(@RequestBody Payment payment){
        int rowsAffected = paymentService.create(payment);
        log.info("受影响记录条数: "+rowsAffected);
        if(rowsAffected>0){
            return new CommonResult(200,"插入成功",rowsAffected+"条记录受影响.");
        }else{
            return new CommonResult(400,"插入失败");
        }

    }

    @GetMapping("/p/get/{id}")
    public CommonResult<Payment> getById(@PathVariable("id") Long id){
        Payment paymentQueryed = paymentService.getPaymentById(id);
        if(paymentQueryed !=null){
            log.info("查询成功");
            System.out.println("sth");
            System.out.println(paymentQueryed.toString());
            return new CommonResult(200,"查询成功",paymentQueryed);
        }else{
            return new CommonResult(400,"查询失败","查无此条目");
        }
    }

}
