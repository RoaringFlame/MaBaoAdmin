package com.mabao.admin.controller.vo;

import com.mabao.admin.pojo.Admin;
import com.mabao.admin.util.VoUtil;

import java.util.Date;
import java.util.List;

public class AdminInVO {
    private Long id;                                //工号
    private String username;                            //姓名
    private String password;                        //密码
    private String tel;                           //手机号
    private String email;                           //邮箱
    private RoleVO roleVO;                          //角色
    private List<RoleVO> roleVOList;                //角色列表

    public static AdminInVO generateBy(Admin admin) {
        AdminInVO vo = VoUtil.copyBasic(AdminInVO.class, admin);
        assert vo != null;
        vo.setRoleVO(RoleVO.generateBy(admin.getRole()));
        return vo;
    }

    //新建管理员
    public static Admin generateBy(AdminInVO adminInVO) {
        Admin admin = VoUtil.copyBasic(Admin.class, adminInVO);
        assert admin != null;
        admin.setRole(RoleVO.generateBy(adminInVO.getRoleVO()));
        admin.setCount(0);
        admin.setEnable(true);
        admin.setCreateTime(new Date());
        admin.setWechart("");
        return admin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleVO getRoleVO() {
        return roleVO;
    }

    public void setRoleVO(RoleVO roleVO) {
        this.roleVO = roleVO;
    }

    public List<RoleVO> getRoleVOList() {
        return roleVOList;
    }

    public void setRoleVOList(List<RoleVO> roleVOList) {
        this.roleVOList = roleVOList;
    }
}
