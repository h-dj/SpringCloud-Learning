package cn.hdj.hystrixturbineconsumernode1.controller;


import cn.hdj.hystrixturbineconsumernode1.remote.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: h_dj
 * @Description:
 */
@RestController
public class ConsumerController {
    @Autowired
    private HelloRemote helloRemote;
    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return helloRemote.hello(name);
    }
}
