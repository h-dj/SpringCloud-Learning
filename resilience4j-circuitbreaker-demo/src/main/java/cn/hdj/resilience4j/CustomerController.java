package cn.hdj.resilience4j;

import cn.hdj.bulkhead.service.CoffeeOrderService;
import cn.hdj.bulkhead.service.CoffeeService;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerOpenException;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hdj
 * @version 1.0
 * @date 2020/8/2 下午8:30
 * @description:
 */
@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService coffeeOrderService;

    private CircuitBreaker circuitBreaker;

    /**
     * 注册 "menu" 断路器
     *
     * @param registry
     */
    public CustomerController(CircuitBreakerRegistry registry) {
        circuitBreaker = registry.circuitBreaker("menu");
    }

    @GetMapping("/getCoffeeMenu")
    public List<String> getCoffeeMenu() {
        return Try.ofSupplier(
                CircuitBreaker.decorateSupplier(circuitBreaker, //装饰熔断器
                        () -> coffeeService.coffee()))
                .recover(CircuitBreakerOpenException.class, Collections.emptyList()) //熔断处理
                .get(); //返回结果
    }


    /**
     * @return
     * @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker 绑定断路器 order
     */
    @PostMapping("order")
    @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name = "order")
    public Map<String, Object> createOrder() {
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("name", "布卡");
        log.info("Order {}", stringObjectMap);
        return coffeeOrderService.coffeeOrder(stringObjectMap);
    }
}