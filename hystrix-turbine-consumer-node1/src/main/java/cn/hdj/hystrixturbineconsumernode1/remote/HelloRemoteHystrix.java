package cn.hdj.hystrixturbineconsumernode1.remote;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: h_dj
 * @Date: 2019/4/25 17:01
 * @Description: 创建回调类
 */
@Component
public class HelloRemoteHystrix implements HelloRemote {
    @Override
    public String hello(@RequestParam(value = "name") String name) {
        return "hello" + name + ", this messge send failed ";
    }
}
