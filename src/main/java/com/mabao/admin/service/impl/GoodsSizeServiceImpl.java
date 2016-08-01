package com.mabao.admin.service.impl;

import com.mabao.admin.pojo.GoodsSize;
import com.mabao.admin.repository.GoodsSizeRepository;
import com.mabao.admin.service.GoodsSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 宝物尺码
 * Created by jackie on 2016/07/09.
 */
@Service
public class GoodsSizeServiceImpl implements GoodsSizeService{
    @Autowired
    private GoodsSizeRepository goodsSizeRepository;

    /**
     * ID获取商品
     * @param id            尺寸ID
     * @return              尺寸对象
     */
    @Override
    public GoodsSize get(Long id) {
        return this.goodsSizeRepository.findOne(id);
    }
}
