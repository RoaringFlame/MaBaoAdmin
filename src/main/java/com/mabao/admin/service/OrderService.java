package com.mabao.admin.service;

import com.mabao.admin.controller.vo.GoodsVO;
import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.controller.vo.OrderInVO;
import com.mabao.admin.controller.vo.OrderOutVO;
import com.mabao.admin.enums.OrderStatus;
import com.mabao.admin.util.PageVO;
import com.mabao.admin.util.Selector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 订单业务接口
 */
public interface OrderService {

    /**
     * 根据需求查询订单
     * @param flag                  查询状态0是包括全部状态，2是待发货页面状态
     * @param orderId               订单号
     * @param orderStatus           订单状态
     * @param page                  页数
     * @param pageSize              每页大小
     * @return                      GoodsVO的PageVO
     */
    PageVO<OrderOutVO> selectOrder(int flag,Long orderId,
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

    /**
     * 下拉框显示订单状态
     *
     * @return          List<Selector>
     */
    List<Selector> orderStateSelector(int flag);

    /**
     * 订单批量导出
     *
     * @param request                   请求
     * @param response                  响应
     * @param orderInVO                 参数VO
     */
    void exportDataOrder(HttpServletRequest request, HttpServletResponse response, OrderInVO orderInVO);
}
