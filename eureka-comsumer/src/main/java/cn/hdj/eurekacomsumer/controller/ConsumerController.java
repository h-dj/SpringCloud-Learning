package cn.hdj.eurekacomsumer.controller;

import cn.hdj.eurekacomsumer.feign.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: h_dj
 * @Date: 2019/4/25 14:08
 * @Description:
 */
@RestController
public class ConsumerController {


    private HelloRemote helloRemote;

    @Autowired
    public void setHelloRemote(HelloRemote helloRemote) {
        this.helloRemote = helloRemote;
    }

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return helloRemote.hello123(name);
    }
}
