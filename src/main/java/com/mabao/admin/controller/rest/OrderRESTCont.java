package com.mabao.admin.controller.rest;

import com.mabao.admin.controller.vo.GoodsVO;
import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.enums.OrderStatus;
import com.mabao.admin.service.OrderService;
import com.mabao.admin.util.PageVO;
import com.mabao.admin.util.Selector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lies on 2016/8/8.
 */

@RestController
@RequestMapping(value = "/order")
public class OrderRESTCont {
    @Autowired
    private OrderService orderService;

    /**
     * order页面初始化下拉框
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Selector> orderInit() {
        return OrderStatus.toList();
    }

    /**
     * 根据需求查询订单
     * @param orderId               订单号
     * @param Consignee             收货人
     * @param state           订单状态
     * @param page                  页数
     * @param pageSize              每页大小
     * @return                      GoodsVO的PageVO
     */
    @RequestMapping(value = "/searchOrder", method = RequestMethod.GET)
    public PageVO<GoodsVO> showSelectGoods(@RequestParam(required = false) Long orderId,
                                           @RequestParam(required = false) String Consignee,
                                           @RequestParam(required = false) OrderStatus state,
                                           @RequestParam(required = false,defaultValue = "1") int page,
                                           @RequestParam(required = false,defaultValue = "8") int pageSize) {
        return this.orderService.selectOrder(orderId,Consignee,state,page,pageSize);
    }

    /**
     * 根据id给相关订单发货
     * @param ids               相关订单id的集合
     * @return
     */
    @RequestMapping(value = "/deliverSomeGoods", method = RequestMethod.GET)
    public JsonResultVO deliverSomeGoods(String ids) {
        return this.orderService.deliverSomeGoods(ids);
    }
}
