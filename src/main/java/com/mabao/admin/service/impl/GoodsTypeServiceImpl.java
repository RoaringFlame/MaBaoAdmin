package com.mabao.admin.service.impl;

import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.pojo.Goods;
import com.mabao.admin.pojo.GoodsType;
import com.mabao.admin.repository.GoodsTypeRepository;
import com.mabao.admin.service.GoodsTypeService;
import com.mabao.admin.util.Selector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    @Override
    public Page<GoodsType> getAllGoodsType(int page, int pageSize) {
        return this.goodsTypeRepository.findAll(new PageRequest(page, pageSize));
    }

    /**
     *获取商品的所有类别
     * @return                  商品类别的集合
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

    /**
     * 删除商品
     * @param typeId             需要删除商品的id
     */
    @Override
    public JsonResultVO deleteGoodsType(Long typeId) {
        try{
            this.goodsTypeRepository.delete(typeId);
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"成功删除！");
    }

    /**
     * 添加商品类别
     * @param goodsType         新增商品类别信息
     */
    @Override
    public JsonResultVO addGoodsType(GoodsType goodsType) {
        GoodsType t = this.goodsTypeRepository.saveAndFlush(goodsType);
        if(t != null) {
            return new JsonResultVO(JsonResultVO.SUCCESS, "添加成功");
        }  else {
            return new JsonResultVO(JsonResultVO.FAILURE, "添加失败");
        }
    }

    @Override
    public Page<GoodsType> selectGoodsType(String goodsTypeName,int page, int pageSize) {
        return this.goodsTypeRepository.findByTypeNameLike(goodsTypeName,new PageRequest(page, pageSize));
    }
}
