package com.mabao.admin.controller.rest;

import com.mabao.admin.controller.vo.GoodsVO;
import com.mabao.admin.enums.State;
import com.mabao.admin.pojo.Goods;
import com.mabao.admin.pojo.GoodsType;
import com.mabao.admin.service.GoodsService;
import com.mabao.admin.util.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/goods")
public class GoodsRESTController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 根据需求查询商品
     * @param goodsTypeId                   商品类别
     * @param state                         商品状态
     * @param title                         商品名称
     * @param articleNumber                 商品货号
     * @param page                          页数
     * @param pageSize                      每页大小
     * @return
     */
    @RequestMapping(value = "/selectGoods",method = RequestMethod.GET)
    public PageVO<GoodsVO> showSelectGoods(@RequestParam(required = false)Long goodsTypeId,
                                           @RequestParam(required = false)Boolean state,
                                           @RequestParam(required = false)String title,
                                           @RequestParam(required = false) String articleNumber,
                                           int page, int pageSize) {
        Page<Goods> goodsList =  this.goodsService.selectGoods(goodsTypeId,state,title,articleNumber,page,pageSize);
        PageVO<GoodsVO> voPage = new PageVO<>();
        voPage.toPage(goodsList);
        voPage.setItems(GoodsVO.generateBy(goodsList.getContent()));
        return voPage;
    }

    /**
     * 通过传入商品id删除商品
     * @param ids               删除商品集合的字符串
     */
    @RequestMapping(value = "/deleteSomeGoods",method = RequestMethod.GET)
    public void deleteSomeGoods(String ids) {
        this.goodsService.deleteSomeGoods(ids);
    }
}
