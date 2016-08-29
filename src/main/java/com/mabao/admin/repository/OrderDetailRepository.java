package com.mabao.admin.repository;


import com.mabao.admin.pojo.OrderDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends BaseRepository<OrderDetail> {
    @Query("DELETE FROM OrderDetail orderDetail WHERE orderDetail.order.id = ?1 ")
    void deleteByStateNotIn(Long id);

}
