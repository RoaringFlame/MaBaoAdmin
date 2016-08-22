package com.mabao.admin.repository;

import com.mabao.admin.pojo.Admin;
import com.mabao.admin.pojo.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends BaseRepository<Admin>{

    /**
     * 分页查询所有后台管理员
     * @param pageable 分页
     */
    Page<Admin> findAll(Pageable pageable);

    @Query(value = "from Admin a where a.username like %?1%")
    Page<Admin> findLikeUsername(String username,Pageable pageable);

    @Query(value = "from Admin a where a.role=?1 and a.username like %?2%")
    Page<Admin> findByRoleAndLikeUsername(Role role, String username, Pageable pageable);

}
