package com.mabao.admin.service;


import com.mabao.admin.controller.vo.JsonResultVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface GoodsManageService {

    /**
     * 商品批量导出
     * @param request
     * @param response
     * @param goodsTypeId            商品类别id
     * @param state                  是否上架
     * @param title                  商品名称
     * @param articleNumber          货号
     * @return
     */
    public JsonResultVO exportDataGoodsDetail(HttpServletRequest request, HttpServletResponse response,Long goodsTypeId, Boolean state,String title,String articleNumber);
}
