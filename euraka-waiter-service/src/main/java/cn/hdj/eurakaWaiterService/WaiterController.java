package cn.hdj.eurakaWaiterService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/coffee")
    public List<String> coffeeMenu() {
        return Arrays.asList("ESPRESSO COFFEES", "HANDMADE COFFEES", "SOOTHING HOT ALTERNATIVES", "COLD ALTERNATIVES");
    }
}
