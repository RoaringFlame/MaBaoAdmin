package com.mabao.admin.service;

import com.mabao.admin.pojo.User;
import com.mabao.admin.util.Selector;

import java.util.List;

/**
 * Created by lies on 2016/7/28.
 */
public interface UserService {

    /**
     * 查询所有用户信息
     * @return
     */
    List<User> getAllUser();

    /**
     * 根据用户id删除某个用户
     * @param id    用户id
     */
    void deleteUser(Long id);
}
