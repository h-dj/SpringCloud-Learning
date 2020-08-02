package cn.hdj.customerService.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author hdj
 * @version 1.0
 * @date 2020/8/1 下午11:10
 * @description:
 */
@Slf4j
@Component
public class FallbackCoffeeService implements CoffeeService {

    @Override
    public List<String> coffee() {
        log.warn("Fallback to EMPTY menu.");
        return Collections.emptyList();
    }
}