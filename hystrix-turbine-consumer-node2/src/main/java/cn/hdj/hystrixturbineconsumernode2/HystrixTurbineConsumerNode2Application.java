package cn.hdj.hystrixturbineconsumernode2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
public class HystrixTurbineConsumerNode2Application {
	public static void main(String[] args) {
		SpringApplication.run(HystrixTurbineConsumerNode2Application.class, args);
	}
}
