package com.mabao.admin.util.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class HistoryLog {
    @AfterReturning("execution(* com.mabao.admin.service.GoodsService.changeGoodsState(String,Boolean))" +
            "&& args(ids,state)")
    public void changeGoodsState(String ids, Boolean state) {
        System.out.println("---------------id=" + ids + "-------------state=" + state);
    }
}