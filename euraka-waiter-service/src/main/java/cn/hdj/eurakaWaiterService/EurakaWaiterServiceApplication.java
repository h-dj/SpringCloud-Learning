package cn.hdj.eurakaWaiterService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EurakaWaiterServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurakaWaiterServiceApplication.class, args);
	}
}
