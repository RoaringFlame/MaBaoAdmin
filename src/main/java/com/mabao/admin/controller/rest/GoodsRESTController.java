package com.mabao.admin.controller.rest;

import com.mabao.admin.controller.vo.GoodsVO;
import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.enums.State;
import com.mabao.admin.pojo.Goods;
import com.mabao.admin.pojo.GoodsType;
import com.mabao.admin.service.GoodsService;
import com.mabao.admin.service.GoodsTypeService;
import com.mabao.admin.util.PageVO;
import com.mabao.admin.util.Selector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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
    public JsonResultVO deleteSomeGoods(String ids) {
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
     * 修改商品信息
     * @param goods             传入商品
     */
    @RequestMapping(value = "/updateGoods", method = RequestMethod.GET)
    public Goods updateGoods(Goods goods) {
        return this.goodsService.saveGoods(goods);
    }


    /**
     * 获得所有商品信息
     * @param   model         map
     * @return
     */
    @RequestMapping(method = GET)
    public JsonResultVO goodsList(int page, int pageSize,Model model) {
        try{
            Map<String,Object> map = new HashMap<>();
            //获得所有商品的类别
            List<Selector> goodsTypeList = this.goodsTypeService.getAllGoodsTypeForSelector();
            map.put("goodsType",goodsTypeList);
            //商品的状态
            List<Selector> state = State.toList();
            map.put("state",state);
            //获得所有商品的信息
            Page<Goods> goodsList = this.goodsService.getAllGoods(page,pageSize);
            map.put("allGoods",goodsList);
            model.addAllAttributes(map);
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"显示成功！");



    }

}
