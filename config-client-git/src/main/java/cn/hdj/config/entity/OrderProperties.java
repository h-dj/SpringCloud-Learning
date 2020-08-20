package cn.hdj.config.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author hdj
 * @version 1.0
 * @date 2020/8/20 下午10:49
 * @description:
 */
@ConfigurationProperties("order")
@RefreshScope
@Data
@Component
public class OrderProperties {
    private Integer discount = 100;
}
