package com.mabao.admin.service;


import com.mabao.admin.pojo.User;

public interface UserService {

    /**
     * 根据ID获取用户对象
     * @param userId            用户ID
     * @return                  用户对象
     */
    User get(Long userId);

    /**
     * 修改用户信息
     * @param user                  用户
     * @return                      修改的用户
     */
    User updateUser(User user);

}
