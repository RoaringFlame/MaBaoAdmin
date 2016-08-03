package com.mabao.admin.service.impl;

import com.mabao.admin.controller.vo.UserInVO;
import com.mabao.admin.pojo.GoodsType;
import com.mabao.admin.pojo.User;
import com.mabao.admin.repository.UserRepository;
import com.mabao.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    /**
     * 获得所有用户信息
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public Page<User> getAllUser(int page, int pageSize) {
        return this.userRepository.findAll(new PageRequest(page, pageSize));
    }

    /**
     * 修改用户信息
     * @param userInVO                  用户
     * @return                      修改的用户
     */
    @Override
    public User updateUser(UserInVO userInVO) {
        User user = new User();
        user.setId(userInVO.getId());
        user.setName(userInVO.getName());
        user.setPassword(userInVO.getPassword());
        user.setCreateTime(new Date());
        user.setEmail("110xg@qq.com");
        return this.userRepository.save(user);
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
        user.setCreateTime(new Date());
        user.setEmail("110xz@qq.com");
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
        String[] ids = userIds.split(",");
        System.out.print(ids[0]);
        for(String id:ids) {
            this.userRepository.delete(Long.valueOf(id));
        }
    }

    /**
     * 模糊查找用户
     * @param searchKey                 搜索关键字
     * @param page                      页码
     * @param pageSize                  每页数量
     * @return
     */
    @Override
    public Page<User> searchUserName(String searchKey, int page, int pageSize) {
        return this.userRepository.findByNameLike("%"+searchKey+"%",new PageRequest(page-1, pageSize));
    }

}
