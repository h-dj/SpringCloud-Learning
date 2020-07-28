package cn.hdj.customerService.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hdj
 * @version 1.0
 * @date 2020/7/28 下午5:00
 * @description:　　自定义AOP 实现熔断器
 */
@Slf4j
@Aspect
@Component
public class CircuitBreakerAspect {
    //容错阀值
    private static final Integer THRESHOLD = 3;
    private Map<String, AtomicInteger> counter = new ConcurrentHashMap<>();
    private Map<String, AtomicInteger> breakCounter = new ConcurrentHashMap<>();

    @Pointcut("execution(* cn.hdj.customerService.RibbonController.readRemoteMenu(..))")
    public void circuitBreaker() {
    }

    @Around("circuitBreaker()")
    public Object doWithCircuitBreaker(ProceedingJoinPoint pjp) throws Throwable {
        String signature = pjp.getSignature().toLongString();
        log.info("Invoke {}", signature);
        Object retVal;
        try {
            if (counter.containsKey(signature)) {
                if (counter.get(signature).get() > THRESHOLD &&
                        breakCounter.get(signature).get() < THRESHOLD) {
                    log.warn("Circuit breaker return null, break {} times.",
                            breakCounter.get(signature).incrementAndGet());
                    HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                    objectObjectHashMap.put("result","熔断返回");
                    return objectObjectHashMap;
                }
            } else {
                counter.put(signature, new AtomicInteger(0));
                breakCounter.put(signature, new AtomicInteger(0));
            }
            retVal = pjp.proceed();
            counter.get(signature).set(0);
            breakCounter.get(signature).set(0);
        } catch (Throwable t) {
            log.warn("Circuit breaker counter: {}, Throwable {}",
                    counter.get(signature).incrementAndGet(), t.getMessage());
            breakCounter.get(signature).set(0);
            throw t;
        }
        return retVal;
    }
}
