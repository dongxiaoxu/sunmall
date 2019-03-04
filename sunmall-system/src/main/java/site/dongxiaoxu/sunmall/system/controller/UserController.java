package site.dongxiaoxu.sunmall.system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.dongxiaoxu.sunmall.system.service.IUserService;
import site.dongxiaoxu.sunmall.system.model.User;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    @RequestMapping("/getUserByUserName.mvc")
    public User getUserByUserName(String userName) {
        User user;
        user =  this.userService.getUserByUserName(userName);
        user.setEnable("1");
        return user;
    }
}
