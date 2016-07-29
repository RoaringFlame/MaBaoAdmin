package com.mabao.admin.service;


import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.pojo.Goods;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface GoodsManageService {

    /**
     * 商品批量导出
     * @param request
     * @param response
     * @param typeId            商品类别id
     * @param state                  是否上架
     * @param title                  商品名称
     * @param articleNumber          货号
     * @return
     */
    public JsonResultVO exportDataGoodsDetail(HttpServletRequest request, HttpServletResponse response,Long typeId, Boolean state,String title,String articleNumber);


}
