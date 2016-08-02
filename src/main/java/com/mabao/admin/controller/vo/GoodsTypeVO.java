package com.mabao.admin.controller.vo;

/**
 * Created by lies on 2016/8/1.
 */
public class GoodsTypeVO {
    private Long id;                                //一级分类Id
    private String typeName;                        //一级分类名称
    private String unitOfQuantity;                   //数量单位
    private String goodsTypeIntroduction;          //商品类型描述

    public Long getId() {
        return id;
    }

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
