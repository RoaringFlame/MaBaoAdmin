package com.mabao.admin.controller.vo;

import com.mabao.admin.enums.Role;

/**
 * Created by lies on 2016/8/2.
 */
public class UserInVO {
    private Long id;                                //用户编号
    private String name;                            //呢称
    private String password;                        //密码
    private String phone;                           //手机号
    private String email;                           //邮箱
    private Role role;                              //角色信息

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
