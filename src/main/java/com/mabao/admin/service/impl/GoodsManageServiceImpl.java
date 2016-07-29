package com.mabao.admin.service.impl;


import com.mabao.admin.pojo.Goods;
import com.mabao.admin.repository.GoodsRepository;
import com.mabao.admin.service.GoodsManageService;
import com.mabao.admin.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class GoodsManageServiceImpl implements GoodsManageService {
    @Autowired
    public GoodsRepository goodsRepository;


    public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");


    /**
     * 商品批量导出
     *
     * @param request
     * @param response
     * @param typeName          商品类别
     * @param state             是否上架
     * @param title             商品名称
     * @param articleNumber     货号
     * @return JsonResultVO     JsonResultVO
     */
    @Override
    public void exportDataGoodsDetail(HttpServletRequest request, HttpServletResponse response, String typeName, Boolean state, String title, String articleNumber) {
        //设置路径
        String docsPath = request.getSession().getServletContext()
                .getRealPath("");                                //模板文件路径
        docsPath = docsPath.substring(0, docsPath.indexOf("classes"));
        docsPath = docsPath + "src\\main\\webapp\\uploadFile";
        String fileName = "商品数据明细表" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xls";           //导出Excel文件名
        String filePath = docsPath + FILE_SEPARATOR + fileName;
        //数据填充
        ExcelUtil<Goods> ex = new ExcelUtil<>();
        String[] headers = {"编号", "时间", "商品类别", "商品名称", "货号", "价格", "上架", "库存"};
        String[] columns = {"id", "upTime", "typeName", "title", "articleNumber", "price", "state", "stockNumber"};
        List<Goods> dataSet = goodsRepository.findByTypeNameOrStateOrTitleOrArticleNumber(typeName, state, title, articleNumber);
        try {
            OutputStream out = new FileOutputStream(filePath);
            ex.exportExcel("商品数据明细表", headers, columns, dataSet, out, "yy-MM-dd HH:mm:ss");
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ExcelUtil.download(filePath, response);
    }


}
