package com.mabao.admin.controller;

import com.mabao.admin.controller.vo.GoodsVO;
import com.mabao.admin.enums.State;
import com.mabao.admin.pojo.Goods;
import com.mabao.admin.service.GoodsService;
import com.mabao.admin.service.GoodsTypeService;
import com.mabao.admin.service.UserService;
import com.mabao.admin.util.Selector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by lies on 2016/7/30.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;


    /**
     * 获得商品信息
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/getGoods/{goodsId}", method = GET)
    public GoodsVO getGoods(@PathVariable Long goodsId) {
        return GoodsVO.generateBy(this.goodsService.get(goodsId));
    }



    /**
     * 新建应该商品
     * @param goods         传入的商品
     */
    @RequestMapping(value = "/newGoods", method = GET)
    public void newGoods(Goods goods) {
        this.goodsService.newGoods(goods);
    }
}
