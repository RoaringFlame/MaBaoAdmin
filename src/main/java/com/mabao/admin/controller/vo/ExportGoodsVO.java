package com.mabao.admin.controller.vo;


import com.mabao.admin.pojo.Goods;
import com.mabao.admin.util.VoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品简要信息VO
 * Created by lies on 2016/6/30.
 */
public class ExportGoodsVO {

    private Long id;                        //宝物的id
    private String title;                   //宝物的描述
    private String picture;                 //宝物图片
    private Double price;                   //宝物价格
    private String newDegree;               //宝物新旧程度
    private String brandName;               //宝物品牌
    private String size;                    //宝物尺寸

    public static ExportGoodsVO generateBy(Goods goods){
        ExportGoodsVO vo = VoUtil.copyBasic(ExportGoodsVO.class, goods);
        assert vo != null;
        vo.setSize(goods.getSize().getName());
        return vo;
    }
    public static List<ExportGoodsVO> generateBy(List<Goods> goodsList){
        List<ExportGoodsVO> list=new ArrayList<>();
        for (Goods g : goodsList){
            list.add(generateBy(g));
        }
        return list;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getNewDegree() {
        return newDegree;
    }

    public void setNewDegree(String newDegree) {
        this.newDegree = newDegree;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
