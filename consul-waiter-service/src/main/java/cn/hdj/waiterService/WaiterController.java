package cn.hdj.waiterService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author hdj
 * @version 1.0
 * @date 7/3/20 3:10 PM
 * @description:
 */
@Slf4j
@RestController
public class WaiterController {

    @GetMapping(value = "/coffee", produces = "application/json")
    public List<String> coffeeMenu(HttpServletRequest request) {
        log.info("coffee  menu");
        return Arrays.asList(request.getServerPort() + " ", "ESPRESSO COFFEES $12", "HANDMADE COFFEES $15", "SOOTHING HOT ALTERNATIVES $20", "COLD ALTERNATIVES $8");
    }


    @PostMapping(value = "/order", consumes = "application/json")
    public Map<String, Object> coffeeMenu(@RequestBody Map<String, Object> params) {
        log.info("order coffee");
        params.put("status", "success");
        params.put("payMoney", "$19");
        return params;
    }
}
