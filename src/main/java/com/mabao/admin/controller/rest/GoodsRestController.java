package com.mabao.admin.controller.rest;


import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.service.GoodsManageService;
import org.omg.CORBA.portable.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
     * @param typeId           商品类型d
     * @param state                 是否上架
     * @param title                 商品名称
     * @param articleNumber         货号
     * @return
     */
    @RequestMapping(value = "/export/dataGoods",method = RequestMethod.GET)
    public JsonResultVO exportDataGoods(HttpServletRequest request, HttpServletResponse response,Long typeId, Boolean state,String title,String articleNumber){
        return this.goodsManageService.exportDataGoodsDetail(request,response,typeId,state,title,articleNumber);
    }

    /**
     * 商品批量导入
     * @param request
     * @return
     */
    @RequestMapping(value = "/import/dataGoods",method = RequestMethod.POST)
    public JsonResultVO importDataGoods(HttpServletRequest request,String file) throws IOException {
        return this.goodsManageService.importDataGoodsDetail(request,file);
    }

}
