package com.mabao.admin.enums;

import com.mabao.admin.util.Selector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lies on 2016/7/28.
 */
public enum Role {
    Administrator("管理员"),USER("用户");
    private String text;
    Role(String text){
        this.text=text;
    }

    public String getText() {
        return text;
    }

    public static List<Selector> toList() {
        List<Selector> list = new ArrayList<Selector>();
        for (Role v: Role.values()) {
            list.add(new Selector(v.name(), v.getText()));
        }
        return list;
    }
}
