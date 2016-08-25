package com.mabao.admin.repository;

import com.mabao.admin.pojo.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface HistoryRepository extends BaseRepository<History> {
    Page<History> findByAdminUsernameLikeAndOperationTimeBetweenOrderByOperationTimeDesc(
            String username, Date start, Date end, Pageable pageable);

    Page<History> findByAdminUsernameLikeOrderByOperationTimeDesc(String username, Pageable pageable);
}
