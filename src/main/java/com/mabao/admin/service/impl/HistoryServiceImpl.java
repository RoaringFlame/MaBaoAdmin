package com.mabao.admin.service.impl;

import com.mabao.admin.pojo.History;
import com.mabao.admin.repository.HistoryRepository;
import com.mabao.admin.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public Page<History> searchHistory(String username, Date start, Date end, int page, int pageSize) {
        return this.historyRepository.findByAdminUsernameLikeAndOperationTimeBetweenOrderByOperationTimeDesc(
                "%"+username+"%", start, end, new PageRequest(page, pageSize));
    }

    @Override
    public Page<History> serchHistory(String username, int page, int pageSize) {
        return this.historyRepository.findByAdminUsernameLikeOrderByOperationTimeDesc("%"+username+"%", new PageRequest(page, pageSize));
    }
}
