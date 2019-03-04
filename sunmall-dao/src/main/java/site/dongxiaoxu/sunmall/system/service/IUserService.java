package site.dongxiaoxu.sunmall.system.service;

import site.dongxiaoxu.sunmall.system.model.User;

public interface IUserService {
    User getUserByUserName(String userName);
}
