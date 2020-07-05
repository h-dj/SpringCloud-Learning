package cn.hdj.eurekacomsumer.controller;

import cn.hdj.eurekacomsumer.feign.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: h_dj
 * @Date: 2019/4/25 14:08
 * @Description:
 */
@RestController
public class ConsumerController {

    @Autowired
    private CoffeeService coffeeService;


    @RequestMapping("/getCoffeeMenu")
    public List<String> getCoffeeMenu() {
        return coffeeService.coffee();
    }
}
