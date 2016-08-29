package com.mabao.admin.repository.impl;

import com.mabao.admin.repository.custom.OrderDetailDelete;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by Administrator on 2016/8/29.
 */
public class OrderDetailRepositoryImpl implements OrderDetailDelete{
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public int deleteByOrderId(Long id) {
        String delete = "delete OrderDetail o where o.order.id = :id";
        Query query = em.createQuery(delete);
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
