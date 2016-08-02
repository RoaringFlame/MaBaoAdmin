package com.mabao.admin.controller.rest;

import com.mabao.admin.controller.vo.GoodsInVO;
import com.mabao.admin.controller.vo.GoodsInitVO;
import com.mabao.admin.controller.vo.GoodsVO;
import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.enums.GoodsState;
import com.mabao.admin.enums.Quality;
import com.mabao.admin.enums.Role;
import com.mabao.admin.pojo.Goods;
import com.mabao.admin.service.GoodsService;
import com.mabao.admin.service.GoodsTypeService;
import com.mabao.admin.util.PageVO;
import com.mabao.admin.util.Selector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/goods")
public class GoodsRESTController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsTypeService goodsTypeService;

    /**
     * 根据需求查询商品
     *
     * @param goodsTypeId   商品类别
     * @param state         商品状态
     * @param title         商品名称
     * @param articleNumber 商品货号
     * @param page          页数
     * @param pageSize      每页大小
     * @return
     */
    @RequestMapping(value = "/selectGoods", method = RequestMethod.GET)
    public PageVO<GoodsVO> showSelectGoods(@RequestParam(required = false) Long goodsTypeId,
                                           @RequestParam(required = false) Boolean state,
                                           @RequestParam(required = false) String title,
                                           @RequestParam(required = false) String articleNumber,
                                           int page, int pageSize) {
        Page<Goods> goodsList = this.goodsService.selectGoods(goodsTypeId, state, title, articleNumber, page, pageSize);
        PageVO<GoodsVO> voPage = new PageVO<>();
        voPage.toPage(goodsList);
        voPage.setItems(GoodsVO.generateBy(goodsList.getContent()));
        return voPage;
    }

    /**
     * 通过传入商品id删除商品
     * @param ids 删除商品集合的字符串
     */
    @RequestMapping(value = "/deleteSomeGoods", method = RequestMethod.GET)
    public JsonResultVO deleteSomeGoods(@RequestParam String ids) {
        return this.goodsService.deleteSomeGoods(ids);
    }

    /**
     * 改变选择商品状态
     * @param ids   选择商品id
     * @param state 需要改成的状态
     */
    @RequestMapping(value = "/changeSomeGoods", method = RequestMethod.GET)
    public JsonResultVO changeSomeGoods(String ids, Boolean state) {
        return this.goodsService.changeGoodsState(ids, state);
    }

    /**
     * 获得商品信息
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/getGoods", method = GET)
    public GoodsInVO getGoods(@RequestParam Long goodsId) {
        return GoodsInVO.generateBy(this.goodsService.get(goodsId));
    }

    /**
     * 修改商品信息
     * @param goodsInVO             传入商品
     */
    @RequestMapping(value = "/updateGoods", method = RequestMethod.GET)
    public Goods updateGoods(GoodsInVO goodsInVO) {
        return this.goodsService.saveGoods(goodsInVO);
    }


    /**
     * 获得所有商品信息
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/goodsList",method = GET)
    public PageVO<GoodsVO> goodsList(int page, int pageSize) {
        Page<Goods> pageGoods = this.goodsService.getAllGoods(page,pageSize);
        PageVO<GoodsVO> voPage = new PageVO<>();
        voPage.toPage(pageGoods);
        voPage.setItems(GoodsVO.generateBy(pageGoods.getContent()));
        return voPage;
    }

    /**
     * 添加商品
     * @param goodsInVO
     * @return
     */
    @RequestMapping(value = "/addGoods",method = POST)
    public JsonResultVO addGoods(GoodsInVO goodsInVO) {
        try{
           this.goodsService.newGoods(goodsInVO);
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"添加成功！");
    }

    /**
     * goods页面初始化下拉框
     * @return
     */
    @RequestMapping(method = GET)
    public GoodsInitVO GoodsInit() {
        GoodsInitVO goodsInitVO = new GoodsInitVO();
        //商品类别
        List<Selector> goodsTypeList =  this.goodsTypeService.getAllGoodsTypeForSelector();
        goodsInitVO.setGoodsTypeList(goodsTypeList);
        //新旧级别
         List<Selector>  newDegreeList = Quality.toList();
        goodsInitVO.setNewDegreeList(newDegreeList);
        //商品上下架状态
        List<Selector>  stateList = GoodsState.toList();
        goodsInitVO.setRoleList(stateList);
        return goodsInitVO;
    }
}
