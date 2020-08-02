package cn.hdj.bulkhead;

import cn.hdj.bulkhead.service.CoffeeOrderService;
import cn.hdj.bulkhead.service.CoffeeService;
import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
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
    private Bulkhead bulkhead;
    /**
     * 代码方式
     * 注册 "menu" 断路器
     * 注册 "menu" 限流器
     */
    public CustomerController(CircuitBreakerRegistry circuitBreakerRegistry,
                              BulkheadRegistry bulkheadRegistry) {
        circuitBreaker = circuitBreakerRegistry.circuitBreaker("menu");
        bulkhead = bulkheadRegistry.bulkhead("menu");
    }


    @GetMapping("/getCoffeeMenu")
    public List<String> getCoffeeMenu() {
        return Try.ofSupplier(
                Bulkhead.decorateSupplier(bulkhead,
                        CircuitBreaker.decorateSupplier(circuitBreaker,
                                () -> coffeeService.coffee())))
                .recover(CircuitBreakerOpenException.class, Collections.emptyList()) //熔断处理
                .recover(BulkheadFullException.class, Collections.emptyList())
                .get(); //返回结果
    }


    /**
     * @return
     * @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker 注解方式断路器 order
     * @io.github.resilience4j.bulkhead.annotation.Bulkhead 注解方式限流 order
     */
    @PostMapping("order")
    @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name = "order")
    @io.github.resilience4j.bulkhead.annotation.Bulkhead(name = "order")
    public Map<String, Object> createOrder() {
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("name", "布卡");
        log.info("Order {}", stringObjectMap);
        return coffeeOrderService.coffeeOrder(stringObjectMap);
    }
}