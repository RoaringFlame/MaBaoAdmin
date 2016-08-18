package com.mabao.admin.service;

import com.mabao.admin.controller.vo.AdminInVO;
import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.pojo.Admin;
import org.springframework.data.domain.Page;

public interface AdminService {
    /**
     * 查询后台用户信息
     * @param adminId       用户编号
     */
    AdminInVO get(Long adminId);

    /**
     * 新建后台用户
     * @param adminInVO 包含用户id的Vo
     */
    Admin newAdmin(AdminInVO adminInVO);

    /**
     * 保存后台用户信息
     * @param adminInVO 包含用户id的Vo
     */
    Admin saveAdmin(AdminInVO adminInVO);

    /**
     * 获取所有后台用户信息
     */
    Page<Admin> getAllAdmin(int page,int pageSize);

    /**
     * 删除选中的用户信息
     */
    JsonResultVO deleteSomeAdmins(String ids);

    /**
     * 查找管理员
     */
    Page<Admin> searchAdmins(Long roleId,String username,int page,int pageSize);
}

