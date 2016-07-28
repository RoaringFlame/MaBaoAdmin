package com.mabao.admin.service;


import com.mabao.admin.pojo.Baby;

public interface BabyService {

    /**
     * ID获取宝宝信息
     * @param babyId        宝宝ID
     * @return              宝宝对象
     */
    Baby get(Long babyId);

    /**
     * 编辑宝宝信息
     * @param babyInfo              宝宝对象
     * @return                      宝宝对象
     */
    Baby updateBabyInfo(Baby babyInfo);

    /**
     * 保存宝宝
     * @param baby                      宝宝实体
     * @return                          宝宝
     */
    Baby saveOne(Baby baby);

}
