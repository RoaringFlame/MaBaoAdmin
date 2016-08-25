package com.mabao.admin.service;

import com.mabao.admin.pojo.History;
import org.springframework.data.domain.Page;

import java.util.Date;

public interface HistoryService {
    Page<History> searchHistory(String username, Date start, Date end, int page, int pageSize);

    Page<History> serchHistory(String username,int page, int pageSize);
}
