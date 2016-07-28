package com.mabao.admin.service.impl;

import com.mabao.admin.pojo.GoodsType;
import com.mabao.admin.repository.GoodsTypeRepository;
import com.mabao.admin.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liuming on 2016/6/28.
 * 商品类别业务接口
 */
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeRepository goodsTypeRepository;


    /**
     * 获取商品类型
     * @param typeId            id
     * @return                  商品类型
     */
    @Override
    public GoodsType get(Long typeId) {
        return this.goodsTypeRepository.findOne(typeId);
    }

}
