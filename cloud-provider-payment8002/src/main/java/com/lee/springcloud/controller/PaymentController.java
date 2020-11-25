package com.lee.springcloud.controller;

import com.lee.springcloud.entities.CommonResult;
import com.lee.springcloud.entities.Payment;
import com.lee.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    //private static final LoggerFactory logger=LoggerFactory.getLogger(PaymentController.class);

    @Resource
    private DiscoveryClient discoveryClient; //注意是cloud包下的, 不要导错了导成Netflix
    @Resource
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort ;

    @PostMapping(value="/p/create",produces = "application/json; charset=UTF-8")
    public CommonResult create(@RequestBody Payment payment){
        int rowsAffected = paymentService.create(payment);
        log.info("受影响记录条数: "+rowsAffected);
        if(rowsAffected>0){
            return new CommonResult(200,"插入成功,thru: "+serverPort,rowsAffected+"条记录受影响.");
        }else{
            return new CommonResult(400,"插入失败,thru: "+serverPort);
        }

    }

    @GetMapping(value="/p/get/{id}",produces = "application/json; charset=UTF-8")
    public CommonResult<Payment> getById(@PathVariable("id") Long id){
        Payment paymentQueryed = paymentService.getPaymentById(id);
        if(paymentQueryed !=null){
            log.info("查询成功");
            System.out.println("sth");
            System.out.println(paymentQueryed.toString());
            return new CommonResult(200,"查询成功,thru: "+serverPort,paymentQueryed);
        }else{
            return new CommonResult(400,"查询失败,thru: "+serverPort,"查无此条目");
        }
    }


    @GetMapping(value = "/payment/discovery",produces = "application/json; charset=UTF-8")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("***** element:"+element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

}
