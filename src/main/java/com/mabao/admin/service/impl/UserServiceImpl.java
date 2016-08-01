package com.mabao.admin.service.impl;

import com.mabao.admin.pojo.User;
import com.mabao.admin.repository.UserRepository;
import com.mabao.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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


    @Override
    public List<User> getAllUser() {
        return this.userRepository.findAll();
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

    /**
     * 增加用户信息
     * @param user              用户
     * @return                  增加的用户
     */
    @Override
    public User newUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.delete(userId);
    }

    @Override
    public void deleteSomeUser(String userIds) {
        String[] ids = userIds.split(",");
        for(String id:ids) {
            this.userRepository.delete(Long.valueOf(id));
        }
    }

}
