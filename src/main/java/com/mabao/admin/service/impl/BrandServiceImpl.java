package com.mabao.admin.service.impl;

import com.mabao.admin.pojo.GoodsBrand;
import com.mabao.admin.repository.BrandRepository;
import com.mabao.admin.service.BrandService;
import com.mabao.admin.util.Selector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 品牌
 * Created by jackie on 2016/07/09.
 */
@Service
public class BrandServiceImpl implements BrandService{
    @Autowired
    private BrandRepository brandRepository;

    /**
     * 获取启用的品牌
     * @param status            是否启用
     * @return                  品牌list
     */
    @Override
    public List<GoodsBrand> findByStatus(Boolean status) {
        return this.brandRepository.findByStatus(status);
    }
    /**
     * 获取品牌下拉菜单
     * @return                  Selector
     */
    @Override
    public List<Selector> findBrandForSelector() {
        List<Selector> brandSelector = new ArrayList<>();
        List<GoodsBrand> brandList = this.brandRepository.findByStatus(Boolean.TRUE);
        for (GoodsBrand b : brandList){
            Selector selector = new Selector(b.getId().toString(),b.getBrandName());
            brandSelector.add(selector);
        }
        return brandSelector;
    }

    @Override
    public GoodsBrand get(Long id) {
        return this.brandRepository.findOne(id);
    }
}
