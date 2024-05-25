package com.example.openfeign1.feign;

import com.example.openfeign1.config.FeignConfig;
import com.example.openfeign1.model.OrderDTO;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//FeignConfig局部配置，让指定的微服务生效，在@FeignClient 注解中指定configuration
//@FeignClient(value = "nacos1-order",path = "/order",configuration = FeignConfig.class)
@FeignClient(value = "nacos1-order",path = "/order")
public interface OrderFeignService {

    @RequestMapping(path = "/order")
    String order();

    @RequestMapping(path = "/order1")
    String order1(@RequestParam Long id, @RequestParam String code);

    @RequestMapping(path = "/order2")
    String order2(@SpringQueryMap OrderDTO orderDTO);

    @RequestMapping(path = "/order3",headers = {"Content-Type=application/json;charset=UTF-8"})
    String order3(OrderDTO orderDTO);
}

