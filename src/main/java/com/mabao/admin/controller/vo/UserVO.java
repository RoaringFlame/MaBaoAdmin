package com.mabao.admin.controller.vo;

import java.util.Date;

/**
 * Created by lies on 2016/7/29.
 */
public class UserVO {
    private long id;
    private String name;
    private String role;
    private Date createTime;
    private int loginTime;
    private Date lastOptTime;
    private String optContent;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(int loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLastOptTime() {
        return lastOptTime;
    }

    public void setLastOptTime(Date lastOptTime) {
        this.lastOptTime = lastOptTime;
    }

    public String getOptContent() {
        return optContent;
    }

    public void setOptContent(String optContent) {
        this.optContent = optContent;
    }
}
