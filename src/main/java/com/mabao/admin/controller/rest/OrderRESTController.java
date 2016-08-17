package com.mabao.admin.controller.rest;

import com.mabao.admin.controller.vo.*;
import com.mabao.admin.enums.OrderStatus;
import com.mabao.admin.pojo.Area;
import com.mabao.admin.repository.AreaRepository;
import com.mabao.admin.service.AreaService;
import com.mabao.admin.service.OrderService;
import com.mabao.admin.util.PageVO;
import com.mabao.admin.util.Selector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by lies on 2016/8/8.
 */

@RestController
@RequestMapping(value = "/order")
public class OrderRESTController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AreaService areaService;

    /**
     * order页面初始化下拉框
     * @return
     */
    @RequestMapping(value = "/OrderStatusSelector", method = RequestMethod.GET)
    public List<Selector> orderStatusInit() {
        return OrderStatus.toList();
    }

    /**
     * order高级搜索地址下拉框
     * @return
     */
    @RequestMapping(value = "/areaSelector", method = RequestMethod.GET)
    public List<Selector> orderLocationInit() {
        return this.areaService.findProvinceForSelector();
    }

    /**
     * 获取某省下的市
     */
    @RequestMapping(value = "/province/{provinceId}/allCity",method = RequestMethod.GET)
    public List<Selector> findCityFromProvince(@PathVariable Long provinceId){
        return this.areaService.findCityForSelector(provinceId);
    }

    /**
     * 获取市下的区县
     */
    @RequestMapping(value = "/city/{cityId}/allCounty",method = RequestMethod.GET)
    public List<Selector> findCountyFromCity(@PathVariable Long cityId){
        return this.areaService.findCountyForSelector(cityId);
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
     * 待发货页面显示
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/toBeShipped", method = RequestMethod.GET)
    public PageVO<OrderOutVO> toBeShippedOrder(@RequestParam(required = false,defaultValue = "0") int page,
                                              @RequestParam(required = false,defaultValue = "8") int pageSize) {
        return this.orderService.toBeShippedOrder(page,pageSize);
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
     @RequestMapping(value = "/deleteSomeOrder", method = RequestMethod.GET)
     public JsonResultVO deleteSomeGoods(String ids) {
         return this.orderService.deleteSomeOrder(ids);
     }

    /**
     * 高级查询订单
     * @param orderInVO             传入信息orderInVO
     * @return
     */
    @RequestMapping(value = "/advancedQueryOrder", method = RequestMethod.POST)
    public PageVO<OrderOutVO> advancedQueryOrder(@RequestBody OrderInVO orderInVO,
                                         @RequestParam(required = false,defaultValue = "1") int page,
                                         @RequestParam(required = false,defaultValue = "8") int pageSize) {
       return this.orderService.advancedQueryOrder(orderInVO,page,pageSize);
    }

    /**
     * 订单批量导出
     *
     * @param orderInVO         传入请求对象
     * @param request
     * @param response@RequestBody
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/bulkExportOrder",method = POST)
    public JsonResultVO bulkExportGoods(OrderInVO orderInVO,
                                        HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            this.orderService.exportDataOrder(request,response,orderInVO);
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"导出成功！");
    }

}
