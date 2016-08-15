package com.mabao.admin.controller.vo;


import com.mabao.admin.pojo.Goods;
import com.mabao.admin.util.VoUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商品简要信息VO
 * Created by lies on 2016/6/30.
 */
public class GoodsVO {

    private Long id;                           //宝物的id
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date upTime;                         //宝物的描述
    private String title;                      //宝物新旧程度
    private String articleNumber;               //宝物货号
    private Double price;                       //宝物价格
    private Integer stockNumber;                    //宝物库存
    private String state;                       //宝物状态
    private String typeName;                     //一级类型编号

    public static GoodsVO generateBy(Goods goods){
        GoodsVO vo = VoUtil.copyBasic(GoodsVO.class, goods);
        assert vo != null;
        vo.setState((goods.getState()==true)?"上架":"下架");
        return vo;
    }
    public static List<GoodsVO> generateBy(List<Goods> goodsList){
        List<GoodsVO> list=new ArrayList<>();
        for (Goods g : goodsList){
            list.add(generateBy(g));
        }
        return list;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
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

    public Integer getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(Integer stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
