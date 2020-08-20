package cn.hdj.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ConfigClientGitApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientGitApplication.class, args);
    }
}
