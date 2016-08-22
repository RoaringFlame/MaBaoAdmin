package com.mabao.admin.controller.vo;

import com.mabao.admin.pojo.User;
import com.mabao.admin.util.VoUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserVO {
    private long id;                                //用户编号
    private String name;                            //昵称
    private String role;                            //用户角色
    private int loginTime;                          //登陆次数
    private Date createTime;                        //创建时间
    private Date lastOptTime;                       //最后一次操作时间
    private String optContent;                      //操作内容

    public static UserVO generateBy(User user){
        UserVO vo = VoUtil.copyBasic(UserVO.class, user);
        assert vo != null;
        vo.setId(user.getId());
        vo.setCreateTime(user.getCreateTime());
        vo.setLastOptTime(new Date());
        vo.setOptContent("查询用户信息");
        vo.setLoginTime(23);
        return vo;
    }
    public static List<UserVO> generateBy(List<User> userList){
        List<UserVO> list=new ArrayList<>();
        for (User u : userList){
            list.add(generateBy(u));
        }
        return list;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
