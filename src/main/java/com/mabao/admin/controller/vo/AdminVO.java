package com.mabao.admin.controller.vo;

import com.mabao.admin.pojo.Admin;
import com.mabao.admin.util.VoUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminVO {
    private long id;                                //用户编号
    private String username;                        //用户名
    private RoleVO roleVO;                          //角色
    private Integer count;                          //登录次数
    private Date createTime;                        //账号创建时间
    private Date operationTime;                     //最后操作时间
    private String operation;                       //最后操作内容

    public static AdminVO generateBy(Admin admin) {
        AdminVO vo = VoUtil.copyBasic(AdminVO.class, admin);
        assert vo != null;
        vo.setCreateTime(admin.getCreateTime());
        vo.setOperationTime(admin.getOperationTime());
        vo.setRoleVO(RoleVO.generateBy(admin.getRole()));
        return vo;
    }

    public static List<AdminVO> generateBy(List<Admin> adminList){
        List<AdminVO> list = new ArrayList<>();
        for(Admin a:adminList){
            list.add(generateBy(a));
        }
        return list;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoleVO getRoleVO() {
        return roleVO;
    }

    public void setRoleVO(RoleVO roleVO) {
        this.roleVO = roleVO;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
