package com.mabao.admin.enums;

import com.mabao.admin.util.Selector;

import java.util.ArrayList;
import java.util.List;

/**
 * 适合宝宝类型
 */
public enum BabyType {

    MEN("男"),WOMEN("女"),ALL("所有宝宝") ;
    private String text;
    BabyType(String text){
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
        List<Selector>  list = new ArrayList<Selector>();
        for (BabyType v: BabyType.values()) {
            list.add(new Selector(v.name(), v.getText()));
        }
        return list;
    }
}
