package cn.hdj.ribbonCustomerService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author hdj
 * @version 1.0
 * @date 7/3/20 1:53 PM
 * @description:
 */
@Slf4j
@RestController
public class RibbonController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;


    /**
     * 使用RestTemplate 读取远程服务菜单信息
     *
     * @return
     */
    @GetMapping("/getMenu")
    public Map<String, Object> readRemoteMenu() {

        Map<String, Object> map = new HashMap<>(16);

        //通过服务名称获取服务的请求地址
        log.info("DiscoveryClient: {}", discoveryClient.getClass().getName());
        List<String> list = discoveryClient.getInstances("waiter-service")
                .stream()
                .map(s -> {
                    log.info("Host: {}, Port: {}", s.getHost(), s.getPort());
                    return String.format("Host: %s, Port: %d", s.getHost(), s.getPort());
                })
                .collect(Collectors.toList());

        map.put("remote address", list);

        //请求远程服务
        ParameterizedTypeReference<List<String>> ptr =
                new ParameterizedTypeReference<List<String>>() {
                };
        ResponseEntity<List<String>> responseEntity = restTemplate
                .exchange("http://waiter-service/coffee/", HttpMethod.GET, HttpEntity.EMPTY, ptr);

        responseEntity.getBody().forEach(c -> log.info("Coffee: {}", c));

        map.put("response==>", responseEntity.getBody());
        return map;
    }
}
