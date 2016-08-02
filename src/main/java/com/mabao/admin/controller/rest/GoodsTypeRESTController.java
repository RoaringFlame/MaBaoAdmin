package com.mabao.admin.controller.rest;

import com.mabao.admin.controller.vo.GoodsTypeVO;
import com.mabao.admin.controller.vo.GoodsVO;
import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.pojo.Goods;
import com.mabao.admin.pojo.GoodsType;
import com.mabao.admin.service.GoodsTypeService;
import com.mabao.admin.util.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lies on 2016/7/30.
 */
@RestController
@RequestMapping(value = "/goodsType")
public class GoodsTypeRESTController {
    @Autowired
    private GoodsTypeService goodsTypeService;

    /**
     * 根据传入商品类型id删除相应的商品类型
     * @param ids                   传入的商品类型id的集合字符串
     */
    @RequestMapping(value = "/deleteSomeGoodsType",method = RequestMethod.GET)
    public JsonResultVO deleteSomeGoods(String ids) {
        try{
            String[] strs = ids.split(",");
            for(String id:strs) {
                goodsTypeService.deleteGoodsType(Long.valueOf(id));
            }
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"成功删除！");
    }

    /**
     * 显示所有商品类别
     * @return              goodsType页面显示
     */
    @RequestMapping(method = RequestMethod.GET)
    public PageVO<GoodsTypeVO> showGoodsType(int page, int pageSize) {
            Page<GoodsType> pageGoodsType =this.goodsTypeService.getAllGoodsType(page,pageSize);
            PageVO<GoodsTypeVO> voPage = new PageVO<>();
            voPage.toPage(pageGoodsType);
            voPage.setItems(GoodsTypeVO.generateBy(pageGoodsType.getContent()));
            return voPage;
    }

    /**
     * 新增商品类别
     * @param goodsTypeVO
     * @return               结果VO
     */
    @RequestMapping(value = "/addGoodsType",method = RequestMethod.GET)
    public JsonResultVO AddGoodsType(@RequestParam GoodsTypeVO goodsTypeVO){
        return this.goodsTypeService.addGoodsType(goodsTypeVO);
    }

    /**
     * 删除商品类别
     * @return              结果VO
     */
    @RequestMapping(value = "/deleteGoodsType",method = RequestMethod.GET)
    public JsonResultVO AddGoodsType(@RequestParam Long goodsTypeId) {
        return this.goodsTypeService.deleteGoodsType(goodsTypeId);
    }



}
