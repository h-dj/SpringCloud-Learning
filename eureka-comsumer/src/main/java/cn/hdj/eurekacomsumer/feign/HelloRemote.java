package cn.hdj.eurekacomsumer.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: h_dj
 * @Date: 2019/4/25 14:05
 * @Description:
 */
@FeignClient(name= "spring-cloud-producer")   // 服务提供中spring.application.name配置的名称
public interface HelloRemote {

    /**
     * 此类中的方法和远程服务中contoller中的方法名和参数需保持一致。
     * @param name
     * @return
     */
    @RequestMapping(value = "/hello")
    public String hello123(@RequestParam(value = "name") String name);
}
