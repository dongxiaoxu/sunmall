package site.dongxiaoxu.sunmall.system.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.dongxiaoxu.sunmall.system.model.User;
import site.dongxiaoxu.sunmall.system.service.IUserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    @RequestMapping("/getUserByUserName.mvc")
    public User getUserByUserName(String userName) {
        User user;
        if (userName == null) {
            user = null;
        } else {
            user =  this.userService.getUserByUserName(userName);
        }
        log.info("返回的user信息:" + String.valueOf(user));
        return user;
    }

    @RequestMapping("/getEnableUsers.mvc")
    public List<User> getEnableUsers(String userName) {
        return this.userService.getEnableUsers(userName);
    }

    @RequestMapping("/updateUserPassword.mvc")
    public String updateUserPassword(String userName, String newPassword) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(newPassword)) {
            return "ERROR";
        }
        boolean result = this.userService.updateUserPassword(userName, newPassword);
        return result ? "SUCCESS" : "FAILURE";

    }

    @RequestMapping("/deleteDisableUser.mvc")
    public String deleteDisableUser(String userName, String newPassword) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(newPassword)) {
            return "ERROR";
        }
        boolean result = this.userService.deleteDisableUser(userName);
        return result ? "SUCCESS" : "FAILURE";

    }

    @RequestMapping("/getUserList.mvc")
    public List<User> getUserList(String enable) {
        return this.userService.getUserList(enable);

    }

    @RequestMapping("/getUserBySql.mvc")
    public List<User> getUserBySql(String userName) {
        return this.userService.getUserBySql(userName);

    }

    @RequestMapping("getUserInfoOrderByName.mvc")
    public List<User> getUserInfoOrderByName() {
        return this.userService.getUserInfoOrderByName();
    }
}
