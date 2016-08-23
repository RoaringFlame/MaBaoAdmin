package com.mabao.admin.util.aop;

import com.mabao.admin.controller.vo.AdminInVO;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class HistoryLog {

    @Autowired
    private WriteInLogService writeInLogService;

    @AfterReturning("execution(* com.mabao.admin.service.GoodsService.changeGoodsState(String,Boolean))" +
            "&& args(ids,state)")
    public void changeGoodsState(String ids, Boolean state) {
        System.out.println("---------------id=" + ids + "-------------state=" + state);
    }

    @AfterReturning("execution(* com.mabao.admin.service.AdminService.newAdmin(..))" +
            "&& args(adminInVO)")
    public void newAdmin(AdminInVO adminInVO) {
        String opt = "新增后台用户"+ adminInVO.getUsername();
        this.writeInLogService.writeOperation(opt);
    }

    @AfterReturning("execution(* com.mabao.admin.service.AdminService.saveAdmin(..))" +
            "&& args(adminInVO)")
    public void saveAdmin(AdminInVO adminInVO) {
        String opt = "修改"+ adminInVO.getUsername()+"的信息";
        this.writeInLogService.writeOperation(opt);
    }

    @Before("execution(* com.mabao.admin.service.AdminService.deleteSomeAdmins(String))" +
            "&& args(ids)")
    public void deleteSomeAdmins(String ids) {
        this.writeInLogService.deleteSomeAdmins(ids);
    }
}