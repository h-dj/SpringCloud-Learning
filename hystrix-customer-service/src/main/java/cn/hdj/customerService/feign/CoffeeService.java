package cn.hdj.customerService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Auther: h_dj
 * @Date: 2019/4/25 14:05
 * @Description:
 */
@FeignClient(name = "waiter-service", contextId = "coffee",
        qualifier = "coffeeService",
        fallback = FallbackCoffeeService.class)
// 服务提供中spring.application.name配置的名称
//contextId 用于作为Bean的名称，区分不同的FeiginClient
//qualifier: 首选Feign客户端
//fallback: 熔断时选择的客户端
//注意不要在类上添加 @RequestMapping() 注解
public interface CoffeeService {


    @GetMapping(value = "/coffee", produces = "application/json")
    public List<String> coffee();
}
