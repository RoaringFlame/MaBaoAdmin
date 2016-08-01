package com.mabao.admin.service;


import com.mabao.admin.pojo.GoodsType;
import com.mabao.admin.util.Selector;

import java.util.List;

public interface GoodsTypeService {

    /**
     * 获取商品类型
     * @param typeId            id
     * @return                  商品类型
     */
    GoodsType get(Long typeId);

    /**
     * 获取商品的所有类别
     * @return                 商品类别的集合
     */
    List<GoodsType> getAllGoodsType();

    /**
     *获取商品的集合
     * @return          Selector集合
     */
    List<Selector> getAllGoodsTypeForSelector();

    /**
     * 删除商品的集合
     * @param typeId
     */
    void deleteGoodsType(Long typeId);
}
