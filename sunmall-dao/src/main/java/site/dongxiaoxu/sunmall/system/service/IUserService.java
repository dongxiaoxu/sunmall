package site.dongxiaoxu.sunmall.system.service;

import site.dongxiaoxu.sunmall.system.model.User;

import java.util.List;

public interface IUserService {
    User getUserByUserName(String userName);

    List<User> getEnableUsers(String userName);

    boolean updateUserPassword(String userName, String newPassword);

    boolean deleteDisableUser(String userName);

    List<User> getUserList(String enable);

    List<User> getUserBySql(String userName);

    List<User> getUserInfoOrderByName();
}