package site.dongxiaoxu.sunmall.order;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @RequestMapping("/getOrderByUserName")
    public String getOrderByUser(String userName) {
        return "12323";
    }
}
