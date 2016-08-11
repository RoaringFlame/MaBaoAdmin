package com.mabao.admin.controller.vo;

import com.mabao.admin.pojo.GoodsType;
import com.mabao.admin.util.VoUtil;

import java.util.ArrayList;
import java.util.List;

public class GoodsTypeVO {
    private Long id;                                //一级分类Id
    private String typeName;                        //一级分类名称
    private String units;                           //数量单位
<<<<<<< HEAD
    private String description;                        //商品类型描述
=======
    private String description;                     //商品类型描述
>>>>>>> dev
    private int goodsNumber;                        //商品数量

    public static GoodsTypeVO generateBy(GoodsType goodsType){
        GoodsTypeVO vo = VoUtil.copyBasic(GoodsTypeVO.class, goodsType);
        assert vo != null;
        vo.setGoodsNumber(100);
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

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
