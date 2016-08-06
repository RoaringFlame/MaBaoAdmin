package com.mabao.admin.controller;

import com.mabao.admin.controller.vo.GoodsVO;
import com.mabao.admin.pojo.Goods;
import com.mabao.admin.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by lies on 2016/7/30.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

}
