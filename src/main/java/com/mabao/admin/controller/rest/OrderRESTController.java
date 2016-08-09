package com.mabao.admin.controller.rest;

import com.mabao.admin.controller.vo.GoodsVO;
import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.controller.vo.OrderOutVO;
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
public class OrderRESTController {
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
     * @param state                 订单状态
     * @param page                  页数
     * @param pageSize              每页大小
     * @return                      GoodsVO的PageVO
     */
    @RequestMapping(value = "/searchOrder", method = RequestMethod.GET)
    public PageVO<OrderOutVO> showSelectGoods(@RequestParam(required = false) Long orderId,
                                              @RequestParam(required = false) OrderStatus state,        
                                              @RequestParam(required = false,defaultValue = "1") int page,
                                              @RequestParam(required = false,defaultValue = "8") int pageSize) {
        return this.orderService.selectOrder(orderId,state,page,pageSize);
    }

    /**
     * 根据给定ids相关相关订单状态
     * @param ids               相关订单id的集合
     * @return
     */
    @RequestMapping(value = "/changeSomeOrder", method = RequestMethod.POST)
    public JsonResultVO changeSomeGoods(String ids,OrderStatus orderStatus) {
        return this.orderService.changeOrderState(ids,orderStatus);
    }

    /**
     * 删除部分订单
     * @param ids               相关订单id的集合
     * @return
     */
     @RequestMapping(value = "/deleteSomeOrder", method = RequestMethod.POST)
     public JsonResultVO deleteSomeGoods(String ids) {
         return this.orderService.deleteSomeOrder(ids);
     }


}
