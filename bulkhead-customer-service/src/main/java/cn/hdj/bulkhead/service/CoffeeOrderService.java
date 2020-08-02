package cn.hdj.bulkhead.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @Auther: h_dj
 * @Date: 2019/4/25 14:05
 * @Description:
 */
@FeignClient(name = "waiter-service", contextId = "coffeeOrder")
// 服务提供中spring.application.name配置的名称
//contextId 用于作为Bean的名称，区分不同的FeiginClient
//注意不要在类上添加 @RequestMapping() 注解
public interface CoffeeOrderService {


    @PostMapping(value = "/order", produces = "application/json")
    public Map<String, Object> coffeeOrder(@RequestBody Map<String, Object> params);
}
