package com.mabao.admin.service;


import com.mabao.admin.controller.vo.GoodsTypeVO;
import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.pojo.GoodsType;
import com.mabao.admin.util.Selector;
import org.springframework.data.domain.Page;

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
     * 模糊查询商品类别
     * @param searchKey                 搜索关键字
     * @param page                      页码
     * @param pageSize                  每页数量
     * @return                          分页GoodsTypeVO
     */
    Page<GoodsType> searchGoodsType(String searchKey,int page, int pageSize);

    /**
     * 修改商品类别
     * @param goodsTypeVO       商品类型信息
     * @return                  结果VO
     */
    JsonResultVO changeGoodsType(GoodsTypeVO goodsTypeVO);

    /**
     * 查找商品类别
     * @param typeId
     * @return
     */
     GoodsType findOne(Long typeId);
}
