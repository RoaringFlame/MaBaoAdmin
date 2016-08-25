package com.mabao.admin.controller.vo;

import com.mabao.admin.enums.OrderStatus;
import com.mabao.admin.pojo.Order;
import com.mabao.admin.util.VoUtil;

import java.util.Date;

/**
 * Created by lies on 2016/8/8.
 */
public class OrderOutVO {
    private Long id;                                //订单的id
    private Date createTime;                        //订单创建时间
    private Date portTime;                          //发货时间
    private String state;                           //订单状态，0待支付（买家），1待发货（卖家），2待收货（买家），3已完成（卖家），4已取消（买家）
    private Double totalSum;                        //总价
    private String consignee;                       //收货人
    private String operator;                        //操作人

    public static OrderOutVO generateBy(Order order,String consignee,String operator){
        OrderOutVO vo = VoUtil.copyBasic(OrderOutVO.class, order);
        assert vo != null;
        vo.setConsignee(consignee);
        vo.setState(order.getState().getText());
        vo.setOperator(operator);
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
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getPortTime() {
        return portTime;
    }

    public void setPortTime(Date portTime) {
        this.portTime = portTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
