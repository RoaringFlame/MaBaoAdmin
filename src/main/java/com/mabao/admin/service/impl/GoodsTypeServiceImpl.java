package com.mabao.admin.service.impl;

import com.mabao.admin.pojo.GoodsType;
import com.mabao.admin.repository.GoodsTypeRepository;
import com.mabao.admin.service.GoodsTypeService;
import com.mabao.admin.util.Selector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 获取商品的所有类别
     * @return                 商品类别的集合
     */
    public List<GoodsType> getAllGoodsType() {
        return this.goodsTypeRepository.findAll();
    }

    /**
     *
     * @return
     */
    @Override
    public List<Selector> getAllGoodsTypeForSelector() {
        List<Selector> typeSelector = new ArrayList<>();
        List<GoodsType> typeList = this.goodsTypeRepository.findAll();
        for (GoodsType b : typeList){
            Selector selector = new Selector(b.getId().toString(),b.getTypeName());
            typeSelector.add(selector);
        }
        return typeSelector;
    }

    @Override
    public void deleteGoodsType(Long typeId) {
        this.goodsTypeRepository.delete(typeId);
    }
}
