package cn.hdj.eurekaproducer2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient //添加@EnableDiscoveryClient注解后，项目就具有了服务注册的功能。
@SpringBootApplication
public class EurekaProducer2Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaProducer2Application.class, args);
	}

}
