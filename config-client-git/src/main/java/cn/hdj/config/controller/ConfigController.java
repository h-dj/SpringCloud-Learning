package cn.hdj.config.controller;

import cn.hdj.config.entity.OrderProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hdj
 * @version 1.0
 * @date 2020/8/20 下午10:55
 * @description:
 */
@RestController
public class ConfigController {

    @Autowired
    OrderProperties orderProperties;


    @GetMapping("/getConfig")
    public String get() {
        return "折扣:" + orderProperties.getDiscount();
    }
}
