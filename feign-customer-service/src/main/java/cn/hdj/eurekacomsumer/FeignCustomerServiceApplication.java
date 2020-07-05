package cn.hdj.eurekacomsumer;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @EnableDiscoveryClient :启用服务注册与发现
 * @EnableFeignClients：启用feign进行远程调用
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class FeignCustomerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignCustomerServiceApplication.class, args);
    }

    @Bean
    public CloseableHttpClient httpClient() {
        return HttpClients.custom()
                .setConnectionTimeToLive(30, TimeUnit.SECONDS)
                .evictIdleConnections(30, TimeUnit.SECONDS)
                .setMaxConnTotal(200)
                .setMaxConnPerRoute(20)
                .disableAutomaticRetries()
                .setKeepAliveStrategy(new ConnectionKeepAliveStrategy() {
                    private final long DEFAULT_SECONDS = 30;

                    @Override
                    public long getKeepAliveDuration(HttpResponse response, HttpContext httpContext) {
                        return Arrays.asList(response.getHeaders(HTTP.CONN_KEEP_ALIVE))
                                .stream()
                                .filter(h -> StringUtils.equalsIgnoreCase(h.getName(), "timeout")
                                        && StringUtils.isNumeric(h.getValue()))
                                .findFirst()
                                .map(h -> NumberUtils.toLong(h.getValue(), DEFAULT_SECONDS))
                                .orElse(DEFAULT_SECONDS) * 1000;
                    }
                })
                .build();
    }
}
