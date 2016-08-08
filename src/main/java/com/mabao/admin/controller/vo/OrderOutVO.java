package com.mabao.admin.controller.vo;

import com.mabao.admin.enums.OrderStatus;
import com.mabao.admin.pojo.GoodsType;
import com.mabao.admin.pojo.Order;
import com.mabao.admin.util.VoUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lies on 2016/8/8.
 */
public class OrderOutVO {
    private Long id;                                //订单的id
    private Date createTime;                        //订单创建时间
    private OrderStatus state;                      //订单状态，0待支付（买家），1待发货（卖家），2待收货（买家），3已完成（卖家），4已取消（买家）
    private Double totalSum;                        //总价
    private String Consignee;                       //收货人

    public static OrderOutVO generateBy(Order order,String Consignee){
        OrderOutVO vo = VoUtil.copyBasic(OrderOutVO.class, order);
        assert vo != null;
        vo.setConsignee(Consignee);
        vo.setState(order.getState());
        return vo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getConsignee() {
        return Consignee;
    }

    public void setConsignee(String consignee) {
        Consignee = consignee;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public OrderStatus getState() {
        return state;
    }

    public void setState(OrderStatus state) {
        this.state = state;
    }
}
