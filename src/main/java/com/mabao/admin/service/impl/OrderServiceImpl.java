package com.mabao.admin.service.impl;

import com.mabao.admin.controller.vo.GoodsVO;
import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.controller.vo.OrderInVO;
import com.mabao.admin.controller.vo.OrderOutVO;
import com.mabao.admin.enums.OrderStatus;
import com.mabao.admin.pojo.Goods;
import com.mabao.admin.pojo.Order;
import com.mabao.admin.repository.AddressRepository;
import com.mabao.admin.repository.BaseDao;
import com.mabao.admin.repository.OrderRepository;
import com.mabao.admin.service.AddressService;
import com.mabao.admin.service.AdminService;
import com.mabao.admin.service.OrderService;
import com.mabao.admin.service.UserService;
import com.mabao.admin.util.ExcelUtil;
import com.mabao.admin.util.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private AddressService addressService;
    @Autowired
    private BaseDao baseDao;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AdminService adminService;
    public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");

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
        if ( orderStatus != null && !"".equals(orderStatus)) {
            args.add(orderStatus);
            str.append(" and o.state = ?");
            str.append(args.size());
        }
        String jpqlAll = JPQL + str.toString();
        String count = JPQLCount + str.toString();
        //分页时从第0页算起
        try {
            PageVO<Order> pages = this.baseDao.findAll(jpqlAll, count, args.toArray(), page - 1, pageSize);
            PageVO<OrderOutVO> pageVO = new PageVO<>();
            List<OrderOutVO> list = new ArrayList<>();
            for (Order order : pages.getItems()) {
                list.add(OrderOutVO.generateBy(order,
                        order.getAddress().getRecipients(),
                        this.adminService.get(order.getOperatorId()).getUsername()));
            }
            pageVO.setItems(list);
            pageVO.setCurrentPage(pages.getCurrentPage() + 1);
            pageVO.setTotalRow(pages.getTotalRow());
            pageVO.setPageSize(pages.getPageSize());
            return pageVO;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 待发货页面初始化
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageVO<OrderOutVO> toBeShippedOrder(int flag,Long orderId,OrderStatus state,int page,int pageSize) {
        try {
            if (flag == 0) {
                List<OrderStatus> list = new ArrayList();
                list.add(OrderStatus.ToBePaid);
                list.add(OrderStatus.Canceled);
                Page<Order> pageOrder = this.orderRepository.findOrderByStateNotIn(list, new PageRequest(page, pageSize));
                PageVO<OrderOutVO> pageVO = new PageVO<>();
                List<OrderOutVO> orderOutVOList = new ArrayList();
                for (Order o : pageOrder.getContent()) {
                    orderOutVOList.add(OrderOutVO.generateBy(o,
                            o.getAddress().getRecipients(),
                            this.adminService.get(o.getOperatorId()).getUsername()));
                }
                pageVO.setItems(orderOutVOList);
                pageVO.setCurrentPage(pageOrder.getNumber() + 1);
                pageVO.setTotalRow(pageOrder.getTotalElements());
                pageVO.setPageSize(pageOrder.getSize());
                return pageVO;
            } else {
                return selectOrder(orderId, state, page+1, pageSize);
            }
        } catch (Exception e) {
            return null;
        }
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

    /**
     * 高级查询订单信息
     * @param orderInVO             传入信息
     * @param page                  页数
     * @param pageSize              每页大小
     * @return
     */
    @Override
    public PageVO<OrderOutVO> advancedQueryOrder(OrderInVO orderInVO, int page, int pageSize) {
        String JPQL = "select o from Order o ";
        String JPQLCount = "select count(o) from Order o ";
        StringBuilder str = new StringBuilder("where 1 = 1 ");
        List<Object> args = new ArrayList<>();
        //订单号
        if (orderInVO.getId() != null && !"".equals(orderInVO.getId())) {
            args.add(orderInVO.getId());
            str.append(" and o.id = ?");
            str.append(args.size());
        }
        //模糊查找买家名字
        if (orderInVO.getBuyerName() != null && !"".equals(orderInVO.getBuyerName())) {
            args.add("%"+orderInVO.getBuyerName()+"%");
            str.append(" and o.buyer.name like ?");
            str.append(args.size());
        }
        //模糊查找收货人
        if (orderInVO.getConsignee() != null && !"".equals(orderInVO.getConsignee())) {
            args.add("%"+orderInVO.getConsignee()+"%");
            str.append(" and o.address.recipients like ?");
            str.append(args.size());
        }
        //模糊查找收货地址
        if (orderInVO.getAddress() != null && !"".equals(orderInVO.getAddress())) {
            args.add("%"+orderInVO.getAddress()+"%");
            str.append(" and o.address.location like ?");
            str.append(args.size());
        }
        //查找用户电话
        if (orderInVO.getPhone() != null && !"".equals(orderInVO.getPhone())) {
            args.add(orderInVO.getPhone());
            str.append(" and o.address.tel = ?");
            str.append(args.size());
        }
        //查找地址地区id
        if (orderInVO.getAreaId() != null && !"".equals(orderInVO.getAreaId())) {
            args.add(orderInVO.getAreaId());
            str.append(" and o.address.area.id = ?");
            str.append(args.size());
        }
        if ( orderInVO.getOrderStatus() != null && !"".equals(orderInVO.getOrderStatus())) {
            args.add(orderInVO.getOrderStatus());
            str.append(" and o.state = ?");
            str.append(args.size());
        }
        //转让时间起
        if (orderInVO.getStartDate() != null && !"".equals(orderInVO.getStartDate())) {
            args.add(orderInVO.getStartDate());
            str.append(" and o.dealTime >= ?");
            str.append(args.size());
        }
        //转让时间始
        if (orderInVO.getEndDate() != null && !"".equals(orderInVO.getEndDate())) {
            args.add(orderInVO.getEndDate());
            str.append(" and o.dealTime <= ?");
            str.append(args.size());
        }
        String jpqlAll = JPQL + str.toString();
        String count = JPQLCount + str.toString();
        try {
            //分页时从第0页算起
            PageVO<Order> pages = this.baseDao.findAll(jpqlAll, count, args.toArray(), page - 1, pageSize);
            PageVO<OrderOutVO> pageVO = new PageVO<>();
            List<OrderOutVO> orderOutVOList = new ArrayList();
            String strs = null;
            for (Order o : pages.getItems()) {
                orderOutVOList.add(OrderOutVO.generateBy(o,
                        o.getAddress().getRecipients(),
                        this.adminService.get(o.getOperatorId()).getUsername()));
            }
            pageVO.setItems(orderOutVOList);
            pageVO.setCurrentPage(pages.getCurrentPage() + 1);
            pageVO.setTotalRow(pages.getTotalRow());
            pageVO.setPageSize(pages.getPageSize());
            return pageVO;
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public void exportDataOrder(HttpServletRequest request, HttpServletResponse response, OrderInVO orderInVO) {

    }

    /**
     * 订单批量导出
     *
     * @param request
     * @param response
     * @param orderInVO
     *//*
    @Override
    public void exportDataOrder(HttpServletRequest request, HttpServletResponse response, OrderInVO orderInVO) {
        //设置路径
        String docsPath = request.getSession().getServletContext()
                .getRealPath("");                                                   //模板文件路径
        docsPath = docsPath.substring(0, docsPath.indexOf("classes"));              //获得类似“temp.test.'”
        docsPath = docsPath + "src\\main\\webapp\\uploadFile";
        String fileName = "订单数据明细表" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xls";           //导出Excel文件名
        String filePath = docsPath + FILE_SEPARATOR + fileName;
        //数据填充
        ExcelUtil<OrderOutVO> ex = new ExcelUtil<>();
        String[] headers = {"订单号", "下单时间", "收货人", "应付金额", "订单状态"};
        String[] columns = {"id", "createTime", "Consignee", "totalSum", "state"};
        String JPQL = "select o from Order o ";
        String JPQLCount = "select count(o) from Order o ";
        StringBuilder str = new StringBuilder("where 1 = 1 ");
        List<Object> args = new ArrayList<>();
        //订单号
        if (orderInVO.getId() != null && !"".equals(orderInVO.getId())) {
            args.add(orderInVO.getId());
            str.append(" and o.id = ?");
            str.append(args.size());
        }
        //模糊查找买家名字
        if (orderInVO.getBuyerName() != null && !"".equals(orderInVO.getBuyerName())) {
            args.add("%"+orderInVO.getBuyerName()+"%");
            str.append(" and o.buyer.name like ?");
            str.append(args.size());
        }
        //模糊查找收货人
        if (orderInVO.getConsignee() != null && !"".equals(orderInVO.getConsignee())) {
            args.add("%"+orderInVO.getConsignee()+"%");
            str.append(" and o.address.recipients like ?");
            str.append(args.size());
        }
        //模糊查找收货地址
        if (orderInVO.getAddress() != null && !"".equals(orderInVO.getAddress())) {
            args.add("%"+orderInVO.getAddress()+"%");
            str.append(" and o.address.location like ?");
            str.append(args.size());
        }
        //查找用户电话
        if (orderInVO.getPhone() != null && !"".equals(orderInVO.getPhone())) {
            args.add(orderInVO.getPhone());
            str.append(" and o.address.tel = ?");
            str.append(args.size());
        }
        //查找地址地区id
        if (orderInVO.getAreaId() != null && !"".equals(orderInVO.getAreaId())) {
            args.add(orderInVO.getAreaId());
            str.append(" and o.address.area.id = ?");
            str.append(args.size());
        }
        if (orderInVO.getOrderStatus() != null && !"".equals(orderInVO.getOrderStatus())) {
            args.add(orderInVO.getOrderStatus());
            str.append(" and o.state = ?");
            str.append(args.size());
        }
        //转让时间起
        if (orderInVO.getStartDate() != null && !"".equals(orderInVO.getStartDate())) {
            args.add(orderInVO.getStartDate());
            str.append(" and o.dealTime >= ?");
            str.append(args.size());
        }
        //转让时间始
        if (orderInVO.getEndDate() != null && !"".equals(orderInVO.getEndDate())) {
            args.add(orderInVO.getEndDate());
            str.append(" and o.dealTime <= ?");
            str.append(args.size());
        }
        String jpqlAll = JPQL + str.toString();
        List<Order> data = this.baseDao.findAll(jpqlAll,args.toArray());
        List<OrderOutVO> list = new ArrayList();
        for (Order o : data) {
            list.add(OrderOutVO.generateBy(o,
                    o.getAddress().getRecipients(),
                    this.adminService.get(o.getOperatorId()).getUsername()));
        }
        try {
            OutputStream out = new FileOutputStream(filePath);
            ex.exportExcel("订单数据明细表", headers, columns, list, out, "yy-MM-dd HH:mm:ss");
            ExcelUtil.download(filePath, response);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}
