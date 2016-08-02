package com.mabao.admin.controller.vo;


import com.mabao.admin.enums.GoodsState;
import com.mabao.admin.pojo.Goods;
import com.mabao.admin.pojo.GoodsType;
import com.mabao.admin.util.VoUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商品简要信息VO
 * Created by lies on 2016/6/30.
 */
public class GoodsVO {

    private Long id;                           //宝物的id
    private Date uptime;                         //宝物的描述
    private String title;                      //宝物新旧程度
    private String articleNumber;               //宝物货号
    private Double price;                       //宝物价格
    private int stockNumber;                    //宝物库存
    private String state;                       //宝物状态
    private Long typeId;                     //一级类型编号

    public static GoodsVO generateBy(Goods goods){
        GoodsVO vo = VoUtil.copyBasic(GoodsVO.class, goods);
        assert vo != null;
        vo.setState((goods.getState()==true)?"上架":"下架");
        vo.setTypeId(goods.getType().getId());
        return vo;
    }
    public static List<GoodsVO> generateBy(List<Goods> goodsList){
        List<GoodsVO> list=new ArrayList<>();
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

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(int stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}
