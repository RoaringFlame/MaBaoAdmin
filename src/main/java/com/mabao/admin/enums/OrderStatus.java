package com.mabao.admin.enums;

import com.mabao.admin.util.Selector;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单状态
 * 0所有状态,1待支付，2待发货，3待收货，4已完成，5已取消
 */
public enum OrderStatus {
    ToBePaid("等待付款"),
    ToBeSend("等待出库"),
    ToBeReceipt("已出库"),
    Completed("交易完成"),
    Canceled("已取消");

    private String text;
    OrderStatus(String text){
        this.text=text;
    }

    public String getText() {
        return text;
    }

    /**
     * 获取枚举的值
     * @return 返回性别下拉列表中的值的集合
     */
    public static List<Selector> toList() {
        List<Selector>  list = new ArrayList<>();
        for (OrderStatus v: OrderStatus.values()) {
            list.add(new Selector(v.name(), v.getText()));
        }
        return list;
    }
}
