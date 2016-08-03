package com.mabao.admin.controller.rest;

import com.mabao.admin.controller.vo.GoodsTypeVO;
import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.pojo.GoodsType;
import com.mabao.admin.service.GoodsTypeService;
import com.mabao.admin.util.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 商品分类
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
    public JsonResultVO deleteSomeGoods(@RequestParam String ids) {
        return this.goodsTypeService.deleteGoodsType(ids);
    }

    /**
     * 显示所有商品类别
     * @return              goodsType页面显示
     */
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public PageVO<GoodsTypeVO> showGoodsType(@RequestParam(required = false ,defaultValue = "1") int page,
                                             @RequestParam(required = false ,defaultValue = "10") int pageSize) {
            Page<GoodsType> pageGoodsType =this.goodsTypeService.getAllGoodsType(page,pageSize);
            PageVO<GoodsTypeVO> voPage = new PageVO<>();
            voPage.toPage(pageGoodsType);
            voPage.setItems(GoodsTypeVO.generateBy(pageGoodsType.getContent()));
            return voPage;
    }

    /**
     * 模糊查询商品类别
     * @param searchKey                 搜索关键字
     * @param page                      页码
     * @param pageSize                  每页数量
     * @return                          分页GoodsTypeVO
     */
    @RequestMapping(value = "/searchGoodsType",method = RequestMethod.GET)
    public PageVO<GoodsTypeVO> searchGoodsType(@RequestParam String searchKey,
                                               @RequestParam(required = false ,defaultValue = "1") int page,
                                               @RequestParam(required = false ,defaultValue = "10") int pageSize) {
        Page<GoodsType> pageGoodsType =this.goodsTypeService.searchGoodsType(searchKey,page,pageSize);
        PageVO<GoodsTypeVO> voPage = new PageVO<>();
        voPage.toPage(pageGoodsType);
        voPage.setItems(GoodsTypeVO.generateBy(pageGoodsType.getContent()));
        return voPage;
    }

    /**
     * 新增商品类别
     * @param goodsTypeVO       录入商品类型信息
     * @return                  结果VO
     */
    @RequestMapping(value = "/addGoodsType",method = RequestMethod.POST)
    public JsonResultVO createGoodsType(@RequestBody GoodsTypeVO goodsTypeVO){
        return this.goodsTypeService.createGoodsType(goodsTypeVO);
    }
}
