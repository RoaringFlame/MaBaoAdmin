package com.mabao.admin.controller.rest;

import com.mabao.admin.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lies on 2016/7/30.
 */
@RestController
@RequestMapping(value = "/goodstype")
public class GoodsTypeRESTController {
    @Autowired
    private GoodsTypeService goodsTypeService;

    /**
     * 根据传入商品类型id删除相应的商品类型
     * @param ids                   传入的商品类型id的集合字符串
     */
    @RequestMapping(value = "/deleteSomeGoods",method = RequestMethod.GET)
    public void deleteSomeGoods(String ids) {
        String[] strs = ids.split(",");
        for(String id:strs) {
            goodsTypeService.deleteGoodsType(Long.valueOf(id));
        }
    }
}
