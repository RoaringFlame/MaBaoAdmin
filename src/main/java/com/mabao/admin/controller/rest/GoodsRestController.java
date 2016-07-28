package com.mabao.admin.controller.rest;


import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.service.GoodsManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商品管理模块
 */
@RestController
@RequestMapping("/goodsManager")
public class GoodsRestController {
    @Autowired
    public GoodsManageService goodsManageService;

    /**
     * 商品批量导出
     * @param request
     * @param response
     * @param goodsTypeId           商品类型d
     * @param state                 是否上架
     * @param title                 商品名称
     * @param articleNumber         货号
     * @return
     */
    @RequestMapping(value = "/export/dataGoods",method = RequestMethod.GET)
    public JsonResultVO exportDataGoods(HttpServletRequest request, HttpServletResponse response,Long goodsTypeId, Boolean state,String title,String articleNumber){
        return this.goodsManageService.exportDataGoodsDetail(request,response,goodsTypeId,state,title,articleNumber);
    }
}
