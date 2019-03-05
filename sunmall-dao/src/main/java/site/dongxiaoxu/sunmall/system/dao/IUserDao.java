package site.dongxiaoxu.sunmall.system.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.dongxiaoxu.sunmall.framework.IBaseDao;
import site.dongxiaoxu.sunmall.system.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IUserDao extends IBaseDao<User> {

    List<User> findByUserName(String userName);

    @Query("from User where userName = :userName and enable = :enable order by createDate desc, password")
    List<User> getEnableUsers(@Param("userName") String userName1, @Param("enable")String enable2);


    @Modifying
    @Transactional
    @Query("update User set password = :password where userName = :userName")
    int updateUserPassword(@Param("userName") String userName, @Param("password") String newPassword);

    @Modifying
    @Transactional
    @Query("delete from User where userName = :userName and enable='0'")
    int deleteDisableUser(@Param("userName") String userName);

    @Query("from User where enable = :enable")
    List<User> getUserList(@Param("enable")String enable, Pageable pageable);


    @Query(nativeQuery = true, value = "select * from sys_user where user_name = ?1")
    List<User> getUserInfoBySql(String userName);

    @Query("from User")
    List<User> getUserInfoOrderByName(Sort sort);


}
