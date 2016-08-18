package com.mabao.admin.controller.rest;

import com.mabao.admin.controller.vo.AdminInVO;
import com.mabao.admin.controller.vo.AdminVO;
import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.controller.vo.RoleVO;
import com.mabao.admin.pojo.Admin;
import com.mabao.admin.pojo.Role;
import com.mabao.admin.service.AdminService;
import com.mabao.admin.service.RoleService;
import com.mabao.admin.util.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/admin")
public class AdminRESTController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/searchAdmin", method = RequestMethod.GET)
    public PageVO<AdminVO> showSelectGoods(@RequestParam(defaultValue = "0") Long roleId,
                                           @RequestParam(defaultValue = "") String username,
                                           @RequestParam(required = false,defaultValue = "0") int page,
                                           @RequestParam(required = false,defaultValue = "8") int pageSize) {
        Page<Admin> list = this.adminService.searchAdmins(roleId, username, page, pageSize);
        PageVO<AdminVO> voPage = new PageVO<>();
        voPage.toPage(list);
        voPage.setItems(AdminVO.generateBy(list.getContent()));
        return voPage;
    }

    @RequestMapping(value = "/getAdmin", method = GET)
    public AdminInVO getAdmin(@RequestParam Long adminId) {
        AdminInVO adminInVO = this.adminService.get(adminId);
        List<Role> list = this.roleService.getAllRole();
        adminInVO.setRoleVOList(RoleVO.generateBy(list));
        return adminInVO;
    }

    @RequestMapping(value = "/addAdmin",method = POST)
        public JsonResultVO addAdmin(@RequestBody AdminInVO adminInVO) {
        try{
            this.adminService.newAdmin(adminInVO);
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"添加成功！");
    }

    @RequestMapping(value = "/deleteSomeAdmin", method = RequestMethod.GET)
    public JsonResultVO deleteSomeGoods(@RequestParam String ids) {
        return this.adminService.deleteSomeAdmins(ids);
    }

    @RequestMapping(value = "/updateAdmin", method = RequestMethod.POST)
    public JsonResultVO updateGoods(@RequestBody AdminInVO adminInVO) {
        try{
            this.adminService.saveAdmin(adminInVO);
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"修改成功！");
    }
}
