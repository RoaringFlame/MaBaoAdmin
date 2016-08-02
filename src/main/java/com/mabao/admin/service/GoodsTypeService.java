package com.mabao.admin.service;


import com.mabao.admin.controller.vo.GoodsTypeVO;
import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.pojo.GoodsType;
import com.mabao.admin.util.Selector;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface GoodsTypeService {

    /**
     * 获取商品类型
     * @param typeId            id
     * @return                  商品类型
     */
    GoodsTypeVO get(Long typeId);

    /**
     * 获取商品的所有类别
     * @return                 商品类别的集合
     */
    Page<GoodsType> getAllGoodsType(int page, int pageSize);

    /**
     *获取商品的集合
     * @return          Selector集合
     */
    List<Selector> getAllGoodsTypeForSelector();

    /**
     * 删除商品的集合
     * @param typeId             需要删除商品的id
     */
    JsonResultVO deleteGoodsType(String typeId);

    /**
     * 新增商品类别
     * @param goodsTypeVO         新增商品类别信息
     */
    JsonResultVO createGoodsType(GoodsTypeVO goodsTypeVO);

    /**
     * 模糊查找类型名称
     * @param goodsTypeName             类型名称
     * @return
     */
    Page<GoodsType> selectGoodsType(String goodsTypeName,int page, int pageSize);


}
