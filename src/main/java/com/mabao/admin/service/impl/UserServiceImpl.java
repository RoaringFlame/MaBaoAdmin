package com.mabao.admin.service.impl;

import com.mabao.admin.pojo.User;
import com.mabao.admin.repository.UserRepository;
import com.mabao.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * 根据ID获取用户对象
     * @param userId            用户ID
     * @return                  用户对象
     */
    @Override
    public User get(Long userId) {
        return this.userRepository.findOne(userId);
    }


    /**
     * 修改用户信息
     * @param user                  用户
     * @return                      修改的用户
     */
    @Override
    public User updateUser(User user) {
        return this.userRepository.saveAndFlush(user);
    }

}
