package cn.hdj.hystrixturbineconsumernode2.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: h_dj
 *
 * name: 服务提供中spring.application.name配置的名称
 * fallback: 指定fallback类，在服务熔断的时候返回fallback类中的内容。
 */
@Primary
@FeignClient(name= "spring-cloud-producer2",fallback = HelloRemoteHystrix.class)
public interface HelloRemote {
    /**
     * 此类中的方法和远程服务中contoller中的方法名和参数需保持一致。
     * @param name
     * @return
     */
    @RequestMapping(value = "/hello")
    public String hello2(@RequestParam(value = "name") String name);
}
