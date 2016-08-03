package com.mabao.admin.service.impl;

import com.mabao.admin.controller.vo.GoodsTypeVO;
import com.mabao.admin.controller.vo.JsonResultVO;
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
    public GoodsTypeVO findOneGoodsType(Long typeId) {
        GoodsType goodsType = this.goodsTypeRepository.findOne(typeId);
        GoodsTypeVO goodsTypeVO = GoodsTypeVO.generateBy(goodsType);
        goodsTypeVO.setGoodsNumber(1000);
        return goodsTypeVO;
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
            for(String id: ids.trim().split(",")) {
                this.goodsTypeRepository.delete(Long.valueOf(id));
            }
            return new JsonResultVO(JsonResultVO.SUCCESS,"成功删除！");
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
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
        goodsType.setDescription(goodsTypeVO.getDescription());
        GoodsType result = this.goodsTypeRepository.save(goodsType);
        if(result != null) {
            return new JsonResultVO(JsonResultVO.SUCCESS, "添加成功");
        }  else {
            return new JsonResultVO(JsonResultVO.FAILURE, "添加失败");
        }
    }

    /**
     * 模糊查询商品类别
     * @param searchKey                 搜索关键字
     * @param page                      页码
     * @param pageSize                  每页数量
     * @return                          分页GoodsTypeVO
     */
    @Override
    public Page<GoodsType> searchGoodsType(String searchKey,int page, int pageSize) {
        return this.goodsTypeRepository.findByTypeNameLike("%"+searchKey+"%",new PageRequest(page-1, pageSize));
    }

    /**
     * 修改商品类别
     * @param goodsTypeVO       商品类型信息
     * @return                  结果VO
     */
    @Override
    public JsonResultVO changeGoodsType(GoodsTypeVO goodsTypeVO) {
        try{
            GoodsType goodsType = this.goodsTypeRepository.findOne(goodsTypeVO.getId());
            goodsType.setTypeName(goodsTypeVO.getTypeName());
            goodsType.setUnits(goodsTypeVO.getUnits());
            goodsType.setDescription(goodsTypeVO.getDescription());
            this.goodsTypeRepository.saveAndFlush(goodsType);
            return new JsonResultVO(JsonResultVO.SUCCESS,"修改成功！");
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
    }
}
