package com.mabao.admin.enums;

import com.mabao.admin.util.Selector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lies on 2016/7/27.
 */
public enum GoodsState {
    SHELVES("上架"),OFFSHELVE("下架");
    private String text;
    GoodsState(String text){
        this.text=text;
    }

    public String getText() {
        return text;
    }

    public static List<Selector> toList() {
        List<Selector> list = new ArrayList<Selector>();
        for (GoodsState v: GoodsState.values()) {
            list.add(new Selector(v.name(), v.getText()));
        }
        return list;
    }
}
