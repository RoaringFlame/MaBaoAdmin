package com.mabao.admin.service;

import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.pojo.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRole();

    Role get(Long roleId);

    Role addRole(Role role);

    Role updateRole(Role role);

    JsonResultVO deleteRole(Long roleId);
}
