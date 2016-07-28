package com.mabao.admin.service.impl;

import com.mabao.admin.pojo.Baby;
import com.mabao.admin.repository.BabyRepository;
import com.mabao.admin.service.BabyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BabyServiceImpl implements BabyService {
    @Autowired
    private BabyRepository babyRepository;


    /**
     * ID获取宝宝信息
     * @param babyId        宝宝ID
     * @return              宝宝对象
     */
    @Override
    public Baby get(Long babyId) {
        return this.babyRepository.findOne(babyId);
    }

    /**
     * 编辑宝宝信息
     * @param babyInfo              宝宝对象
     * @return                      宝宝对象
     */
    @Override
    public Baby updateBabyInfo(Baby babyInfo) {
        return this.babyRepository.saveAndFlush(babyInfo);
    }


    /**
     * 保存宝宝
     * @param baby                      宝宝实体
     * @return                          宝宝
     */
    @Override
    public Baby saveOne(Baby baby) {
        return this.babyRepository.save(baby);
    }


}
