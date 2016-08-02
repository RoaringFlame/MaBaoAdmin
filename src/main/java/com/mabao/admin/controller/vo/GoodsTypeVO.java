package com.mabao.admin.controller.vo;

import com.mabao.admin.pojo.Goods;
import com.mabao.admin.pojo.GoodsType;
import com.mabao.admin.util.VoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lies on 2016/8/1.
 */
public class GoodsTypeVO {
    private Long id;                                //一级分类Id
    private String typeName;                        //一级分类名称
    private String unitOfQuantity;                   //数量单位
    private String goodsTypeIntroduction;          //商品类型描述
    private int goodsNumber;                        //商品数量

    public static GoodsTypeVO generateBy(GoodsType goodsType){
        GoodsTypeVO vo = VoUtil.copyBasic(GoodsTypeVO.class, goodsType);
        assert vo != null;
       vo.setGoodsTypeIntroduction("hhh3");
        vo.setGoodsNumber(100);
        vo.setUnitOfQuantity("匹");
        return vo;
    }
    public static List<GoodsTypeVO> generateBy(List<GoodsType> goodsList){
        List<GoodsTypeVO> list=new ArrayList<>();
        for (GoodsType g : goodsList){
            list.add(generateBy(g));
        }
        return list;
    }

    public int getGoodsNumber() {return goodsNumber;}

    public void setGoodsNumber(int goodsNumber) {this.goodsNumber = goodsNumber;}

    public Long getId() {return id;}

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUnitOfQuantity() {
        return unitOfQuantity;
    }

    public void setUnitOfQuantity(String unitOfQuantity) {
        this.unitOfQuantity = unitOfQuantity;
    }

    public String getGoodsTypeIntroduction() {
        return goodsTypeIntroduction;
    }

    public void setGoodsTypeIntroduction(String goodsTypeIntroduction) {
        this.goodsTypeIntroduction = goodsTypeIntroduction;
    }
}
