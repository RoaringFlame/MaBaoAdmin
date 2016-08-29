package com.mabao.admin.repository;


import com.mabao.admin.pojo.OrderDetail;
import com.mabao.admin.repository.custom.OrderDetailDelete;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends BaseRepository<OrderDetail>,OrderDetailDelete {
}
