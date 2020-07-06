package cn.hdj.waiterService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class WaiterServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(WaiterServiceApplication.class, args);
	}
}
