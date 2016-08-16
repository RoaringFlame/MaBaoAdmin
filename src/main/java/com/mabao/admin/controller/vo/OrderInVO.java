package com.mabao.admin.controller.vo;

import com.mabao.admin.enums.OrderStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by lies on 2016/8/16.
 */
public class OrderInVO {
    private Long id;                    //id
    private String buyerName;           //买家名字
    private String consignee;           //收货人
    private String address;             //地址
    private String phone;               //收货人手机号
    private OrderStatus orderStatus;    //订单状态
    private Long  areaId;               //地区id
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;             //转让日期起
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;               //转让日期始

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
