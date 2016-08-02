package com.mabao.admin.service.impl;

import com.mabao.admin.controller.vo.GoodsTypeVO;
import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.pojo.GoodsType;
import com.mabao.admin.repository.GoodsTypeRepository;
import com.mabao.admin.service.GoodsTypeService;
import com.mabao.admin.util.Copys;
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
    public GoodsTypeVO get(Long typeId) {
        GoodsType goodsType = this.goodsTypeRepository.findOne(typeId);
        GoodsTypeVO goodsTypeVO = GoodsTypeVO.generateBy(goodsType);
        goodsTypeVO.setGoodsNumber(1000);
        return goodsTypeVO;
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
     * @param ids             需要删除商品的id
     */
    @Override
    public JsonResultVO deleteGoodsType(String ids) {
        try{
            String[] strs = ids.split(",");
            for(String id:strs) {
                this.goodsTypeRepository.delete(Long.valueOf(id));
            }
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"成功删除！");
    }

    /**
     * 添加商品类别
     * @param goodsTypeVO                   新增商品类别信息
     */
    @Override
    public JsonResultVO createGoodsType(GoodsTypeVO goodsTypeVO) {
        GoodsType goodsType = new GoodsType();
        goodsType.setTypeName(goodsTypeVO.getTypeName());
        goodsType.setUnits(goodsTypeVO.getUnits());
        goodsType.setDescribe(goodsTypeVO.getDescribe());
        GoodsType result = this.goodsTypeRepository.save(goodsType);
        if(result != null) {
            return new JsonResultVO(JsonResultVO.SUCCESS, "添加成功");
        }  else {
            return new JsonResultVO(JsonResultVO.FAILURE, "添加失败");
        }
    }

    /**
     * 通过商品类别模糊查询
     * @param goodsTypeName             类型名称
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public Page<GoodsType> selectGoodsType(String goodsTypeName,int page, int pageSize) {
        return this.goodsTypeRepository.findByTypeNameLike(goodsTypeName,new PageRequest(page, pageSize));
    }
}
