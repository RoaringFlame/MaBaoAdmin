package com.mabao.admin.service.impl;

import com.mabao.admin.repository.OrderDetailRepository;
import com.mabao.admin.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lies on 2016/8/29.
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Override
    public void detele(Long id) {
        this.orderDetailRepository.deleteByOrderId(id);
    }
}
