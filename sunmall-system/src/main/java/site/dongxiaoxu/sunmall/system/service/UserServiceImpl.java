package site.dongxiaoxu.sunmall.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import site.dongxiaoxu.sunmall.system.dao.IUserDao;
import site.dongxiaoxu.sunmall.system.model.User;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User getUserByUserName(String userName) {
        List<User> users;
        users = this.userDao.findByUserName(userName);
        if (users.size() > 1) {
            throw new IllegalArgumentException("more than one results!");
        }
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public List<User> getEnableUsers(String userName) {
        return this.userDao.getEnableUsers(userName, "1");
    }

    @Override
    public boolean updateUserPassword(String userName, String newPassword) {
        int updateResults;
        updateResults = this.userDao.updateUserPassword(userName, newPassword);
        return updateResults > 0 ? true : false;
    }

    @Override
    public boolean deleteDisableUser(String userName) {
        int updateResults;
        updateResults = this.userDao.deleteDisableUser(userName);
        return updateResults > 0 ? true : false;
    }

    @Override
    public List<User> getUserList(String enable) {
        Pageable pageable;
        pageable = new PageRequest(1, 4);
        List<User> users;
        users = this.userDao.getUserList(enable, pageable);
        return users;
    }

    @Override
    public List<User> getUserBySql(String userName) {
        return this.userDao.getUserInfoBySql(userName);
    }

    @Override
    public List<User> getUserInfoOrderByName() {
        Sort sort;
        sort = new Sort(Sort.Direction.DESC, "createDate").and(new Sort(Sort.Direction.ASC, "password"));
        return this.userDao.getUserInfoOrderByName(sort);
    }
}
