package com.mabao.admin.repository;

import com.mabao.admin.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User> {
    /**
     * 用户名密码获取用户信息
     * @param name              用户名
     * @param password          密码
     * @return                  用户对象
     */
    User findByNameAndPassword(String name, String password);

    /**
     * 用户名获取用户信息
     * SpringSecurity模块UserDetailsService接口使用
     * @param name              用户名
     * @return                  用户对象
     */
    User findByName(String name);

    /**
     * 通过邮箱查用户
     * @param email             邮箱
     * @return                  用户对象
     */
    User findByEmail(String email);

    /**
     * 分页查询所有用户
     * @param pageable              分页
     * @return
     */
    Page<User> findAll(Pageable pageable);

    /**
     * 依据标题模糊查询
     * @param title             标题key
     * @param pageable          分页参数
     * @return                  分页goods
     */
    Page<User> findByNameLike(String title, Pageable pageable);
}
