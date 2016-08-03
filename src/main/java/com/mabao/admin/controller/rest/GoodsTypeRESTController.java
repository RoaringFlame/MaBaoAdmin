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
     * 模糊查询商品类别
     * @param searchKey                 搜索关键字
     * @param page                      页码
     * @param pageSize                  每页数量
     * @return                          分页GoodsTypeVO
     */
    @RequestMapping(value = "/searchGoodsType",method = RequestMethod.GET)
    public PageVO<GoodsTypeVO> searchGoodsType(@RequestParam(required = false ,defaultValue = "") String searchKey,
                                               @RequestParam(required = false ,defaultValue = "1") int page,
                                               @RequestParam(required = false ,defaultValue = "8") int pageSize) {
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

    /**
     * 获取商品分类信息
     * @param goodsTypeId
     * @return
     */
    @RequestMapping(value = "/{goodsTypeId}",method = RequestMethod.GET)
    public GoodsTypeVO findOneGoodsType(@PathVariable Long goodsTypeId) {
        return this.goodsTypeService.findOneGoodsType(goodsTypeId);
    }


    /**
     * 修改商品类别
     * @param goodsTypeVO       商品类型信息
     * @return                  结果VO
     */
    @RequestMapping(value = "/changeGoodsType",method = RequestMethod.POST)
    public JsonResultVO changeGoodsType(@RequestBody GoodsTypeVO goodsTypeVO){
        return this.goodsTypeService.changeGoodsType(goodsTypeVO);
    }
}
