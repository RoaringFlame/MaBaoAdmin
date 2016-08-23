package com.mabao.admin.controller.vo;

import com.mabao.admin.pojo.Admin;
import com.mabao.admin.util.VoUtil;

import java.util.List;

public class AdminInVO {
    private Long id;                                //编号
    private String empno;                           //工号
    private String username;                        //姓名
    private String password;                        //密码
    private String tel;                           //手机号
    private String email;                           //邮箱
    private Long roleId;                           //角色编号
    private List<RoleVO> roleVOList;                //角色列表

    public static AdminInVO generateBy(Admin admin) {
        AdminInVO vo = VoUtil.copyBasic(AdminInVO.class, admin);
        assert vo != null;
        vo.setRoleId(admin.getRole().getId());
        return vo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpno() {
        return empno;
    }

    public void setEmpno(String empno) {
        this.empno = empno;
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<RoleVO> getRoleVOList() {
        return roleVOList;
    }

    public void setRoleVOList(List<RoleVO> roleVOList) {
        this.roleVOList = roleVOList;
    }
}
