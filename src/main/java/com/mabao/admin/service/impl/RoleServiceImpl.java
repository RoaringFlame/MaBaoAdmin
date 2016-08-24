package com.mabao.admin.service.impl;

import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.pojo.Role;
import com.mabao.admin.repository.RoleRepository;
import com.mabao.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRole() {
        return this.roleRepository.findAll();
    }

    @Override
    public Role get(Long roleId) {
        return this.roleRepository.findOne(roleId);
    }

    @Override
    public Role addRole(Role role) {
        return this.roleRepository.saveAndFlush(role);
    }

    @Override
    public Role updateRole(Role role) {
        return this.roleRepository.saveAndFlush(role);
    }

    @Override
    public JsonResultVO deleteRole(Long roleId) {
        try {
            this.roleRepository.delete(roleId);
        } catch (Exception e) {
            return new JsonResultVO(JsonResultVO.FAILURE, e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"成功删除！");
    }

}
