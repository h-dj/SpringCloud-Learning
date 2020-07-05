package cn.hdj.eurakaWaiterService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @author hdj
 * @version 1.0
 * @date 7/3/20 3:10 PM
 * @description:
 */

@RestController
public class WaiterController {

    @GetMapping(value = "/coffee", produces = "application/json")
    public List<String> coffeeMenu(HttpServletRequest request) {
        return Arrays.asList(request.getServerPort()+" ", "ESPRESSO COFFEES $12", "HANDMADE COFFEES $15", "SOOTHING HOT ALTERNATIVES $20", "COLD ALTERNATIVES $8");
    }
}
