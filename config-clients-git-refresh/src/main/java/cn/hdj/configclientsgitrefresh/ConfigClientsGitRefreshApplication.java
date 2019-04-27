package cn.hdj.configclientsgitrefresh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
public class ConfigClientsGitRefreshApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientsGitRefreshApplication.class, args);
	}

}
