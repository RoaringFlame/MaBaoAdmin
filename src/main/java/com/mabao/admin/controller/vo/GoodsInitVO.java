package com.mabao.admin.controller.vo;

import com.mabao.admin.util.Selector;

import java.util.List;

/**
 * Created by lies on 2016/8/2.
 */
public class GoodsInitVO {
    private List<Selector>  goodsTypeList;                 //商品类别
    private List<Selector>  stateList;                     //用户类型
    private List<Selector>  newDegreeList;                 //商品新旧级别
    private List<Selector>  babyTypeList;                  //适合宝贝类别

    public List<Selector> getGoodsTypeList() {
        return goodsTypeList;
    }

    public void setGoodsTypeList(List<Selector> goodsTypeList) {
        this.goodsTypeList = goodsTypeList;
    }

    public List<Selector> getStateList() {
        return stateList;
    }

    public void setStateList(List<Selector> stateList) {
        this.stateList = stateList;
    }

    public List<Selector> getNewDegreeList() {
        return newDegreeList;
    }

    public void setNewDegreeList(List<Selector> newDegreeList) {
        this.newDegreeList = newDegreeList;
    }

    public List<Selector> getBabyTypeList() {
        return babyTypeList;
    }

    public void setBabyTypeList(List<Selector> babyTypeList) {
        this.babyTypeList = babyTypeList;
    }
}
