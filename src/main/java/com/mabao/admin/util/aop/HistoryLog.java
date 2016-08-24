package com.mabao.admin.util.aop;

import com.mabao.admin.controller.vo.AdminInVO;
import com.mabao.admin.pojo.Role;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class HistoryLog {

    @Autowired
    private WriteInLogService writeInLogService;

    @AfterReturning("execution(* com.mabao.admin.service.AdminService.newAdmin(..))" +
            "&& args(adminInVO)")
    public void newAdmin(AdminInVO adminInVO) {
        String opt = "新增后台用户" + adminInVO.getUsername();
        this.writeInLogService.writeOperation(opt);
    }

    @AfterReturning("execution(* com.mabao.admin.service.AdminService.saveAdmin(..))" +
            "&& args(adminInVO)")
    public void updateAdmin(AdminInVO adminInVO) {
        String opt = "修改" + adminInVO.getUsername() + "的信息";
        this.writeInLogService.writeOperation(opt);
    }

    @Before("execution(* com.mabao.admin.service.AdminService.deleteSomeAdmins(String))" +
            "&& args(ids)")
    public void deleteSomeAdmins(String ids) {
        this.writeInLogService.deleteSomeAdmins(ids);
    }

    @AfterReturning("execution(* com.mabao.admin.service.RoleService.addRole(..))" +
            "&&args(role)")
    public void newRole(Role role) {
        String opt = "增加角色" + role.getRoleName();
        this.writeInLogService.writeOperation(opt);
    }

    @AfterReturning("execution(* com.mabao.admin.service.RoleService.updateRole(..))" +
            "&&args(role)")
    public void updateRole(Role role) {
        String opt = "修改角色" + role.getRoleName();
        this.writeInLogService.writeOperation(opt);
    }

    @Before("execution(* com.mabao.admin.service.RoleService.deleteRole(Long))" +
            "&&args(roleId)")
    public void deleteRole(Long roleId) {
        this.writeInLogService.deleteRole(roleId);
    }
}