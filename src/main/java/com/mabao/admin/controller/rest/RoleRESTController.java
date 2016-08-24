package com.mabao.admin.controller.rest;

import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.controller.vo.RoleVO;
import com.mabao.admin.pojo.Role;
import com.mabao.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/role")
public class RoleRESTController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/getRoleSelector", method = GET)
    public List<RoleVO> getRoleSelector() {
        List<Role> list = this.roleService.getAllRole();
        Role role = new Role();
        role.setId((long) 0);
        role.setRoleName("所有");
        role.setName("All");
        list.add(0, role);
        return RoleVO.generateBy(list);
    }
    @RequestMapping(value = "/getRole", method = GET)
    public RoleVO getRole(@RequestParam Long roleId) {
        return RoleVO.generateBy(this.roleService.get(roleId));
    }

    @RequestMapping(value = "/deleteRole", method = GET)
    public JsonResultVO deleteRole(@RequestParam Long roleId) {
        return this.roleService.deleteRole(roleId);
    }

    @RequestMapping(value = "/addRole", method = POST)
    public JsonResultVO addRole(@RequestBody RoleVO roleVO) {
        try {
            this.roleService.addRole(RoleVO.generateBy(roleVO));
        } catch (Exception e) {
            return new JsonResultVO(JsonResultVO.FAILURE, e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS, "添加成功！");
    }

    @RequestMapping(value = "/updateRole", method = POST)
    public JsonResultVO updateRole(@RequestBody RoleVO roleVO) {
        try {
            this.roleService.updateRole(RoleVO.generateBy(roleVO));
        } catch (Exception e) {
            return new JsonResultVO(JsonResultVO.FAILURE, e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS, "修改成功！");
    }
}
