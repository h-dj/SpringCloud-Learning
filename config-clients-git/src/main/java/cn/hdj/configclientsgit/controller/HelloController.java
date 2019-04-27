package cn.hdj.configclientsgit.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: h_dj
 * @Date: 2019/4/27 16:01
 * @Description:
 */
@RestController
public class HelloController {

    @Value("${env.name}")
    private String env_name;

    @RequestMapping("/env_name")
    public String from() {
        return this.env_name;
    }
}
