package com.mabao.admin.service.impl;

import com.mabao.admin.controller.vo.UserInVO;
import com.mabao.admin.pojo.User;
import com.mabao.admin.repository.UserRepository;
import com.mabao.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;


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
     * @param userInVO                  用户
     * @return                      修改的用户
     */
    @Override
    public User updateUser(UserInVO userInVO) {
        User user = this.userRepository.findOne(userInVO.getId());
        user.setName(userInVO.getName());
        user.setPassword(userInVO.getPassword());
        user.setEmail(userInVO.getEmail());
        user.setPhone(userInVO.getPhone());
        return this.userRepository.saveAndFlush(user);
    }

    /**
     * 增加用户信息
     * @param userInVO              用户
     * @return                  增加的用户
     */
    @Override
    public User newUser(UserInVO userInVO) {
        User user = new User();
        user.setName(userInVO.getName());
        user.setPassword(userInVO.getPassword());
        user.setEmail(userInVO.getEmail());
        user.setPhone(userInVO.getPhone());
        user.setCreateTime(new Date());
        return this.userRepository.save(user);
    }

    /**
     * 删除单个用户（需求更改预留）
     * @param userId            用户id
     */
    @Override
    public void deleteUser(Long userId) {
        this.userRepository.delete(userId);
    }

    /**
     * 删除选择用户信息
     * @param userIds               多个用户id的字符串
     */
    @Override
    public void deleteSomeUser(String userIds) {
        String[] ids = userIds.trim().split(",");
        for(String id:ids) {
            this.userRepository.delete(Long.valueOf(id));
        }
    }

    /**
     * 模糊查询用户
     * @param userRole                  用户角色
     * @param searchKey                 用户名
     * @param page                      当前页数
     * @param pageSize                  页数大小
     * @return
     */
    @Override
    public Page<User> searchUserName(String userRole,String searchKey, int page, int pageSize) {
        if("".equals(searchKey)) {
            return this.userRepository.findByNameLike("%" + searchKey + "%", new PageRequest(page - 1, pageSize));
        }
        return this.userRepository.findByNameLike("%" + searchKey + "%", new PageRequest(page - 1, pageSize));
    }

}
