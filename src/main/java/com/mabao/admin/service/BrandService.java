package com.mabao.admin.service;


import com.mabao.admin.pojo.GoodsBrand;
import com.mabao.admin.util.Selector;

import java.util.List;

/**
 * 品牌
 * Created by jackie on 2016/07/09.
 */
public interface BrandService {
    /**
     * 获取启用的品牌
     * @param status            是否启用
     * @return                  品牌list
     */
    List<GoodsBrand> findByStatus(Boolean status);

    /**
     * 获取品牌下拉菜单
     * @return                  Selector
     */
    List<Selector> findBrandForSelector();

    GoodsBrand get(Long id);
}
