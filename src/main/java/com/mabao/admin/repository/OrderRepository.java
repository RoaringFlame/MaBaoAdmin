package com.mabao.admin.repository;


import com.mabao.admin.enums.OrderStatus;
import com.mabao.admin.pojo.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends BaseRepository<Order> {

    //待发货自定义查询,Pageable pageable
    Page<Order> findOrderByStateNotIn(List<OrderStatus> states,Pageable pageable);

}
