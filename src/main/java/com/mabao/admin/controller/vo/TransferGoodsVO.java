package com.mabao.admin.controller.vo;

import com.mabao.admin.enums.BabyType;
import com.mabao.admin.enums.Quality;
import com.mabao.admin.pojo.Goods;
import com.mabao.admin.pojo.GoodsBrand;
import com.mabao.admin.pojo.GoodsSize;
import com.mabao.admin.pojo.GoodsType;
import com.mabao.admin.util.VoUtil;

import java.util.ArrayList;
import java.util.List;

/**.jsp
 * Created by lies on 2016/8/18.
 */
public class TransferGoodsVO {
    private String title;                           //标题
    private Double oldPrice;                        //原价
    private Double price;                           //现价
    private String typeName;                        //二级类型名称
    private String brandName;                       //商品品牌名称
    private Boolean pack;                           //是否有包装，1有0无
    private Boolean receipt;                        //是否有小票，1有0无
    private String newDegree;                      //新旧程度，0表示全新，95，80分别表示95成8成新
    private String babyType;                      //适合宝宝类型
    private String  goodsType;                      //一级类型编号
    private String  goodsBrand;                       //商品品牌ID
    private String goodsSize;                         //尺寸

    public static TransferGoodsVO generateBy(Goods goods) {
        TransferGoodsVO vo = VoUtil.copyBasic(TransferGoodsVO.class, goods);
        vo.setNewDegree(goods.getNewDegree().getText());
        vo.setBabyType(goods.getBabyType().getText());
        vo.setGoodsBrand(goods.getBrandName());
        vo.setGoodsSize(goods.getSize().getName());
        vo.setGoodsType(goods.getTypeName());
        return vo;
    }

    public static List<TransferGoodsVO> generateBy(List<Goods> goodsList){
        List<TransferGoodsVO> list=new ArrayList<>();
        for (Goods g : goodsList){
            list.add(generateBy(g));
        }
        return list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Boolean getPack() {
        return pack;
    }

    public void setPack(Boolean pack) {
        this.pack = pack;
    }

    public Boolean getReceipt() {
        return receipt;
    }

    public void setReceipt(Boolean receipt) {
        this.receipt = receipt;
    }

    public String getNewDegree() {
        return newDegree;
    }

    public void setNewDegree(String newDegree) {
        this.newDegree = newDegree;
    }

    public String getBabyType() {
        return babyType;
    }

    public void setBabyType(String babyType) {
        this.babyType = babyType;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public String getGoodsSize() {
        return goodsSize;
    }

    public void setGoodsSize(String goodsSize) {
        this.goodsSize = goodsSize;
    }
}
