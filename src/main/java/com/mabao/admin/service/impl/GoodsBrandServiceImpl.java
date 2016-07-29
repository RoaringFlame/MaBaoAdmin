package com.mabao.admin.service.impl;

import com.mabao.admin.pojo.GoodsBrand;
import com.mabao.admin.repository.GoodsBrandRepository;
import com.mabao.admin.service.GoodsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 品牌
 * Created by jackie on 2016/07/09.
 */
@Service
public class GoodsBrandServiceImpl implements GoodsBrandService {
    @Autowired
    private GoodsBrandRepository goodsBrandRepository;

    /**
     * ID获取品牌
     * @param brandId           品牌ID
     * @return                  品牌
     */
    @Override
    public GoodsBrand get(Long brandId) {
        return this.goodsBrandRepository.findOne(brandId);
    }
}
