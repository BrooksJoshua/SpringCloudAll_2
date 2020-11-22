package com.lee.springcloud.controller;

import com.lee.springcloud.entities.Payment;
import com.lee.springcloud.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    public static final String REQUEST_URL="http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/c/create")
    public CommonResult<Payment> create(Payment payment){
        log.info("插入");
        return restTemplate.postForObject(REQUEST_URL+"/p/create",payment,CommonResult.class);
    }

    @GetMapping("/c/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id){
        log.info("查询");
        CommonResult forObject = (CommonResult) restTemplate.getForObject(REQUEST_URL + "/p/get/" + id, CommonResult.class);
        System.out.println(forObject.toString());
        return forObject;
    }


}
