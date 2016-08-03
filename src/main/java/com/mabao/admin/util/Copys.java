package com.mabao.admin.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 两个对象同名属性复制值
 * @author chenglixiang
 *
 */
public class Copys {

    public static void copy(Object src, Object dest) {

        Map<String, Object> srcMap = new HashMap<String, Object>();
        Field[] srcFields = src.getClass().getDeclaredFields();
        for (Field fd : srcFields) {
            try {
                srcMap.put(fd.getName(), fd.get(src)); //获取属性值
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Field[] destFields = dest.getClass().getDeclaredFields();
        for (Field fd : destFields) {
            if (srcMap.get(fd.getName()) == null) {
                continue;
            }
            try {
                fd.set(dest, srcMap.get(fd.getName())); //给属性赋值
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

