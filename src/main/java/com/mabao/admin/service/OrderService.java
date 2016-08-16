package com.mabao.admin.service;

import com.mabao.admin.controller.vo.GoodsVO;
import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.controller.vo.OrderInVO;
import com.mabao.admin.controller.vo.OrderOutVO;
import com.mabao.admin.enums.OrderStatus;
import com.mabao.admin.util.PageVO;

/**
 * 订单业务接口
 */
public interface OrderService {

    /**
     * 根据需求查询订单
     * @param orderId               订单号
     * @param orderStatus           订单状态
     * @param page                  页数
     * @param pageSize              每页大小
     * @return                      GoodsVO的PageVO
     */
    PageVO<OrderOutVO> selectOrder(Long orderId,
                                OrderStatus orderStatus,
                                int page, int pageSize);

    /**
     * 根据id给相关订单发货
     * @param ids               相关订单id的集合
     * @return
     */
    JsonResultVO changeOrderState(String ids,OrderStatus orderStatus);

    /**
     * 删除相关订单
     * @param ids               相关订单id的集合
     * @return
     */
     JsonResultVO deleteSomeOrder(String ids);

    /**
     * 高级查询订单信息
     * @param orderInVO             传入信息
     * @param page                  页数
     * @param pageSize              每页大小
     * @return
     */
    PageVO<OrderOutVO> advancedQueryOrder(OrderInVO orderInVO, int page,int pageSize);
}
