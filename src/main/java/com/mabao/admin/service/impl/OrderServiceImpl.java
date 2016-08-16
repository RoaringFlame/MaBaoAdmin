package com.mabao.admin.service.impl;

import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.controller.vo.OrderOutVO;
import com.mabao.admin.enums.OrderStatus;
import com.mabao.admin.pojo.Order;
import com.mabao.admin.repository.AddressRepository;
import com.mabao.admin.repository.BaseDao;
import com.mabao.admin.repository.OrderRepository;
import com.mabao.admin.service.OrderService;
import com.mabao.admin.util.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private BaseDao baseDao;
    @Autowired
    private OrderRepository orderRepository;

    /**
     * 根据需求查询订单
     * @param orderId               订单号
     * @param orderStatus           订单状态
     * @param page                  页数
     * @param pageSize              每页大小
     * @return
     */
    @Override
    public PageVO<OrderOutVO> selectOrder(Long orderId, OrderStatus orderStatus, int page, int pageSize) {
        String JPQL = "select o from Order o ";
        String JPQLCount = "select count(o) from Order o ";
        StringBuilder str = new StringBuilder("where 1 = 1 ");
        List<Object> args = new ArrayList<>();
        if (orderId != null ) {
            args.add(orderId);
            str.append(" and o.id = ?");
            str.append(args.size());
        }
        if (orderStatus != null && !"".equals(orderStatus)) {
            args.add(orderStatus);
            str.append(" and o.state = ?");
            str.append(args.size());
        }
        String jpqlAll = JPQL + str.toString();
        String count = JPQLCount + str.toString();
        //分页时从第0页算起
        PageVO<Order> pages = this.baseDao.findAll(jpqlAll,count,args.toArray(),page-1,pageSize);
        PageVO<OrderOutVO> pageVO = new PageVO<>();
        List<OrderOutVO> list = new ArrayList<>();
        for(Order order:pages.getItems()) {
           String recipients = this.addressRepository.findOne(order.getAddress().getId()).getRecipients();
            OrderOutVO orderOutVO = OrderOutVO.generateBy(order,recipients);
            list.add(orderOutVO);
        }
        pageVO.setItems(list);
        pageVO.setCurrentPage(pages.getCurrentPage()+1);
        pageVO.setTotalRow(pages.getTotalRow());
        pageVO.setPageSize(pages.getPageSize());
        return pageVO;
    }

    /**
     * 高级查询
     * @param page                  页数
     * @param pageSize              每页大小
     * @return
     */
    @Override
    public PageVO<OrderOutVO> advancedQuery(int page, int pageSize) {
        return null;
    }

    /**
     * 根据给定ids相关相关订单状态
     * @param ids               相关订单id的集合
     * @param orderStatus
     * @return
     */
    @Override
    public JsonResultVO changeOrderState(String ids,OrderStatus orderStatus) {
        try{
            String[] strs = ids.split(",");
            Long orderId;
            Order order;
            for(String id:strs) {
                orderId = Long.valueOf(id);
                order = this.orderRepository.findOne(orderId);
                order.setState(orderStatus);
                this.orderRepository.saveAndFlush(order);
            }
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"成功修改！");
    }

    /**
     *  删除部分订单
     * @param ids               相关订单id的集合
     * @return
     */
    @Override
    public JsonResultVO deleteSomeOrder(String ids) {
        try{
            String[] strs = ids.split(",");
            Long orderId;
            Order order;
            for(String id:strs) {
                orderId = Long.valueOf(id);
               this.orderRepository.delete(orderId);
            }
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"删除修改！");
    }
}
