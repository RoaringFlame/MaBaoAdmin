package com.mabao.admin.service;


import com.mabao.admin.pojo.GoodsBrand;

/**
 * 品牌
 * Created by jackie on 2016/07/09.
 */
public interface GoodsBrandService {

    /**
     * ID获取品牌
     * @param brandId           品牌ID
     * @return                  品牌
     */
    GoodsBrand get(Long brandId);
}
