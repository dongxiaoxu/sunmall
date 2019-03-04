package site.dongxiaoxu.sunmall.system.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import site.dongxiaoxu.sunmall.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


@Data
@Entity
@Table(name = "sys_user")
public class User extends BaseModel {

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "enable")
    private String enable;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_date")
    private Date createDate;

}

