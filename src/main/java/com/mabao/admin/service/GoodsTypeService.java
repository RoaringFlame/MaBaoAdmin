package com.mabao.admin.service;


import com.mabao.admin.pojo.GoodsType;

public interface GoodsTypeService {

    /**
     * 获取商品类型
     * @param typeId            id
     * @return                  商品类型
     */
    GoodsType get(Long typeId);
}
