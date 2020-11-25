package com.lee.springcloud.controller;


import com.lee.springcloud.entities.CommonResult;
import com.lee.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
    //public static final String REQUEST_URL="http://localhost:8001";
    @Resource
    private DiscoveryClient discoveryClient; //注意是cloud包下的, 不要导错了导成Netflix

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/c/create",produces = "application/json; charset=UTF-8")
    public CommonResult<Payment> create(Payment payment){
        log.info("插入");
        return restTemplate.postForObject(PAYMENT_URL+"/p/create",payment,CommonResult.class);
    }

    @GetMapping(value = "/c/get/{id}",produces = "application/json; charset=UTF-8")
    public CommonResult<Payment> get(@PathVariable("id") Long id){
        log.info("查询");
        CommonResult forObject = (CommonResult) restTemplate.getForObject(PAYMENT_URL + "/p/get/" + id, CommonResult.class);
        System.out.println(forObject.toString());
        return forObject;
    }


    @GetMapping(value = "/consumer/discovery",produces = "application/json; charset=UTF-8")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("***** element:"+element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-order-service");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());//日志打印: CLOUD-ORDER-SERVICE	192.168.10.104	80	http://192.168.10.104:80
        }
        return this.discoveryClient;
    }


}
