package com.mabao.admin.service.impl;

import com.mabao.admin.pojo.User;
import com.mabao.admin.repository.UserRepository;
import com.mabao.admin.service.UserService;
import com.mabao.admin.util.Selector;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lies on 2016/7/28.
 */
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        List<User> userList= this.userRepository.findAll();
        return userList;
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.delete(id);
    }


}
