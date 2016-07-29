package com.mabao.admin.service;


import com.mabao.admin.pojo.GoodsSize;

/**
 * 宝物尺码
 * Created by jackie on 2016/07/09.
 */
public interface GoodsSizeService {

    /**
     * ID获取商品
     * @param id            尺寸ID
     * @return              尺寸对象
     */
    GoodsSize get(Long id);
}
