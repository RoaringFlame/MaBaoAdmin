package com.mabao.admin.repository;

import com.mabao.admin.pojo.Admin;
import com.mabao.admin.pojo.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminRepository extends BaseRepository<Admin>{

    Admin findByUsername(String username);
    /**
     * 分页查询所有后台管理员
     * @param pageable 分页
     */
    Page<Admin> findAll(Pageable pageable);

    Page<Admin> findByUsernameLike(String username,Pageable pageable);

    Page<Admin> findByRoleAndUsernameLike(Role role, String username, Pageable pageable);

}
