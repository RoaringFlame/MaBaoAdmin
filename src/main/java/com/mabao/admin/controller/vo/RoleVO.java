package com.mabao.admin.controller.vo;

import com.mabao.admin.pojo.Role;
import com.mabao.admin.util.VoUtil;

import java.util.ArrayList;
import java.util.List;

public class RoleVO {
    private Long id;                    //角色编号
    private String name;                //角色代号
    private String roleName;            //角色名

    public static RoleVO generateBy(Role role) {
        RoleVO vo = VoUtil.copyBasic(RoleVO.class, role);
        assert vo != null;
        return vo;
    }

    public static Role generateBy(RoleVO roleVO){
        Role role = VoUtil.copyBasic(Role.class,roleVO);
        assert role != null;
        return role;
    }

    public static List<RoleVO> generateBy(List<Role> roleList) {
        List<RoleVO> list = new ArrayList<>();
        for (Role r : roleList) {
            list.add(generateBy(r));
        }
        return list;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
