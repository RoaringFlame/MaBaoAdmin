package com.mabao.admin.service;


import com.mabao.admin.controller.vo.GoodsVO;
import com.mabao.admin.enums.State;
import com.mabao.admin.pojo.GoodsType;
import com.mabao.admin.pojo.User;
import com.mabao.admin.util.PageVO;

import java.util.List;

public interface UserService {

    /**
     * 根据ID获取用户对象
     * @param userId            用户ID
     * @return                  用户对象
     */
    User get(Long userId);

    /**
     *得到所有用户信息
     * @return
     */
    List<User> getAllUser();

    /**
     * 修改用户信息
     * @param user                  用户
     * @return                      修改的用户
     */
    User updateUser(User user);

    /**
     * 增加用户信息
     * @param user              用户
     * @return                  增加的用户
     */
    User newUser(User user);
    /**
     * 删除用户信息
     * @param userId            用户id
     * @return                  删除用户
     */
    void deleteUser(Long userId);

    /**
     * 删除多个用户信息
     * @param userIds               多个用户id的字符串
     * @return                      删除多个用户
     */
    void deleteSomeUser(String userIds);


}
