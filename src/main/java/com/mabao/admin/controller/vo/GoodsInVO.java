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
    private Double oldPrice;                        //原价
    private Double price;                           //现价
    private Long typeId;                            //类别id
    private String typeName;                        //二级类型名称
    private Long brandId;                           //品牌id
    private String brandName;                       //商品品牌名称
    @DateTimeFormat(pattern = "yyyy-MM-dd")         //存日期时使用
    private Date upTime;                             //上架时间
    private Integer stockNumber;                    //库存数量
    private String message;                         //卖家分享
    private BabyType babyType;
    private String pictureName;                     //上传图片名

    public static GoodsInVO generateBy(Goods goods){
        GoodsInVO vo = VoUtil.copyBasic(GoodsInVO.class, goods);
        assert vo != null;
        vo.setUser_id(goods.getUser().getId());
        vo.setPictureName(goods.getPicture());
        vo.setNewDegree(goods.getNewDegree());
        vo.setBabyType(goods.getBabyType());
        vo.setTypeName(goods.getTypeName());
        vo.setTypeId(goods.getType().getId());
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

    public BabyType getBabyType() {
        return babyType;
    }

    public void setBabyType(BabyType babyType) {
        this.babyType = babyType;
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

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public void setNewDegree(Quality newDegree) {
        this.newDegree = newDegree;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public Integer getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(Integer stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

}

