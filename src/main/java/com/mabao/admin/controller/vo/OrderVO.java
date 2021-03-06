package com.mabao.admin.controller.vo;

import com.mabao.admin.enums.OrderStatus;
import com.mabao.admin.pojo.OrderDetail;
import com.mabao.admin.util.VoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单简要信息VO
 * Created by lies on 2016/6/30.
 */
public class OrderVO {
    private Long orderDetailId;                     //订单明细的id
    private Long goodsId;                           //商品ID
    private String size;                            //尺寸
    private String title;                           //标题
    private String picture;                         //图片
    private Double unitCost;                        //单价
    private String brand;                           //商品品牌
    private Integer quantity;                       //数量
    private Double totalSum;                        //总价
    private Double freight;                         //运费
    private OrderStatus state;                      //订单状态

    public static OrderVO generateBy(OrderDetail o){
        OrderVO vo = VoUtil.copyBasic(OrderVO.class, o);
        assert vo != null;
        vo.setGoodsId(o.getGoods().getId());
        vo.setPicture(o.getGoods().getPicture());
        vo.setOrderDetailId(o.getId());
        vo.setQuantity(o.getOrder().getQuantity());
        vo.setTotalSum(o.getOrder().getTotalSum());
        vo.setFreight(o.getOrder().getFreight());
        return vo;
    }
    public static List<OrderVO> generateBy(List<OrderDetail> orderList){
        List<OrderVO> list=new ArrayList<>();
        for (OrderDetail g : orderList){
            list.add(generateBy(g));
        }
        return list;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public OrderStatus getState() {
        return state;
    }

    public void setState(OrderStatus state) {
        this.state = state;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}
