package cn.hdj.bulkhead.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Auther: h_dj
 * @Date: 2019/4/25 14:05
 * @Description:
 */
@FeignClient(name = "waiter-service", contextId = "coffee")
//注意不要在类上添加 @RequestMapping() 注解
public interface CoffeeService {


    @GetMapping(value = "/coffee", produces = "application/json")
    public List<String> coffee();
}
