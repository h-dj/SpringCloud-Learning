package cn.hdj.customerService;

import cn.hdj.customerService.feign.CoffeeOrderService;
import cn.hdj.customerService.feign.CoffeeService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hdj
 * @version 1.0
 * @date 7/3/20 1:53 PM
 * @description:
 */
@Slf4j
@RestController
public class CoffeeController {


    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private CoffeeOrderService coffeeOrderService;


    @GetMapping("/getCoffeeMenu")
    public List<String> getCoffeeMenu() {
        return coffeeService.coffee();
    }


    @GetMapping("order")
    @HystrixCommand(fallbackMethod = "fallbackCreateOrder")
    public Map<String, Object> order() {
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("name", "布卡");
        return coffeeOrderService.coffeeOrder(stringObjectMap);
    }

    /**
     * 容错处理方法
     *
     * @return
     */
    public Map<String, Object> fallbackCreateOrder() {
        log.warn("Fallback to NULL order.");
        return null;
    }
}
