package com.mabao.admin.service.impl;

import com.mabao.admin.controller.vo.AdminInVO;
import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.pojo.Admin;
import com.mabao.admin.pojo.Role;
import com.mabao.admin.repository.AdminRepository;
import com.mabao.admin.repository.RoleRepository;
import com.mabao.admin.service.AdminService;
import com.mabao.admin.util.VoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private RoleRepository roleRepository;

    /**
     * @param adminId 用户编号
     */
    @Override
    public AdminInVO get(Long adminId) {
        return AdminInVO.generateBy(this.adminRepository.findOne(adminId));
    }

    /**
     * @param adminInVO 包含用户id的Vo
     * @return
     */
    @Override
    public Admin newAdmin(AdminInVO adminInVO) {
        Admin admin = VoUtil.copyBasic(Admin.class, adminInVO);
        assert admin != null;
        admin.setCount(0);
        admin.setEnable(true);
        Date date = new Date();
        admin.setCreateTime(date);
        admin.setWechart("");
        admin.setOperation("");
        admin.setOperationTime(date);
        admin.setRole(this.roleRepository.findOne(adminInVO.getRoleId()));
        return this.adminRepository.saveAndFlush(admin);
    }

    /**
     * 保存信息
     */
    @Override
    public Admin saveAdmin(AdminInVO adminInVO) {
        Admin admin = this.adminRepository.findOne(adminInVO.getId());
        assert admin != null;
        admin.setId(adminInVO.getId());
        admin.setEmpno(adminInVO.getEmpno());
        admin.setUsername(adminInVO.getUsername());
        admin.setRole(this.roleRepository.findOne(adminInVO.getRoleId()));
        admin.setTel(adminInVO.getTel());
        admin.setEmail(adminInVO.getEmail());
        return this.adminRepository.saveAndFlush(admin);
    }

    /**
     *返回所有管理员
     */
    @Override
    public Page<Admin> getAllAdmin(int page, int pageSize) {
        return this.adminRepository.findAll(new PageRequest(page, pageSize));
    }

    /**
     *删除所选管理员
     */
    @Override
    public JsonResultVO deleteSomeAdmins(String ids) {
        try {
            String[] strs = ids.split(",");
            for (String adminId: strs) {
                this.adminRepository.delete(Long.decode(adminId));
            }
        } catch (Exception e) {
            return new JsonResultVO(JsonResultVO.FAILURE, e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"成功删除！");
    }

    @Override
    public Page<Admin> searchAdmins(Long roleId, String username, int page, int pageSize) {
        if(0 == roleId){
            return this.adminRepository.findLikeUsername(username,new PageRequest(page,pageSize));
        }else{
            Role role = this.roleRepository.findOne(roleId);
            return this.adminRepository.findByRoleAndLikeUsername(role,username,new PageRequest(page,pageSize));
        }
    }
}
