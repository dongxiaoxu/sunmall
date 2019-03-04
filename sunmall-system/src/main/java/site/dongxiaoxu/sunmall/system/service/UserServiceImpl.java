package site.dongxiaoxu.sunmall.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import site.dongxiaoxu.sunmall.system.dao.IUserDao;
import site.dongxiaoxu.sunmall.system.model.User;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User getUserByUserName(String userName) {
        User exampleUser = new User();
        exampleUser.setUserName(userName);
        Example<User> userExample = Example.of(exampleUser);
        return this.userDao.findOne(userExample);
    }
}
