package cn.hdj.ribbonCustomerService;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @Author hdj
 * @Date: 7/3/20 1:44 PM
 */
@EnableDiscoveryClient
@SpringBootApplication
public class RibbonCustomerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RibbonCustomerServiceApplication.class, args);
    }

    /**
     * 配置请求线程池
     *
     * @return
     */
    @Bean
    public HttpComponentsClientHttpRequestFactory requestFactory() {
        PoolingHttpClientConnectionManager connectionManager =
                new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(20);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .evictIdleConnections(30, TimeUnit.SECONDS)
                .disableAutomaticRetries()
                // 有 Keep-Alive 认里面的值，没有的话永久有效
                //.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
                // 换成自定义的
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

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory(httpClient);

        return requestFactory;
    }

    /**
     * RestTemplate 客户端
     *
     * @param builder
     * @return
     *
     *  @LoadBalanced 标识使用RestTemplate 作为LoadBalancerClient
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(100))
                .setReadTimeout(Duration.ofMillis(500))
                .requestFactory(this::requestFactory)
                .build();
    }
}
