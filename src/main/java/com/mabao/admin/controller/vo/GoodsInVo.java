package com.mabao.admin.controller.vo;

import com.mabao.admin.enums.BabyType;
import com.mabao.admin.enums.Quality;
import com.mabao.admin.pojo.*;
import com.mabao.admin.util.VoUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lies on 2016/8/1.
 */
public class GoodsInVO {
    private Long id;                                //商品编号，自增
    private String title;                           //标题
    private Long user_id;                           //商品归属者编号，后台用户编号为0
    private Quality newDegree;                      //新旧程度，0表示全新，95，80分别表示95成8成新
    private String message;                         //卖家分享
    private Double price;                           //现价
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//存日期时使用
    private Date purchaseTime;                      //购买时间
    private Date releaseTime;                       //保质期时间
    private String  goodsIntroduction;              //商品介绍

    public static GoodsInVO generateBy(Goods goods){
        GoodsInVO vo = VoUtil.copyBasic(GoodsInVO.class, goods);
        assert vo != null;
        vo.setUser_id(goods.getUser().getId());
        vo.setPurchaseTime(goods.getUpTime());
        vo.setReleaseTime(goods.getUpTime());
        vo.setGoodsIntroduction("hhh3");
        return vo;
    }
    public static List<GoodsInVO> generateBy(List<Goods> goodsList){
        List<GoodsInVO> list=new ArrayList<>();
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Quality getNewDegree() {
        return newDegree;
    }

    public void setNewDegree(Quality newDegree) {
        this.newDegree = newDegree;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getGoodsIntroduction() {
        return goodsIntroduction;
    }

    public void setGoodsIntroduction(String goodsIntroduction) {
        this.goodsIntroduction = goodsIntroduction;
    }
}
