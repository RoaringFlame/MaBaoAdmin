package com.mabao.admin.service;


import com.mabao.admin.controller.vo.UserInVO;
import com.mabao.admin.pojo.User;
import org.springframework.data.domain.Page;

public interface UserService {

    /**
     * 根据ID获取用户对象
     * @param userId            用户ID
     * @return                  用户对象
     */
    User get(Long userId);

    /**
     * 修改用户信息
     * @param userInVO                  用户
     * @return                      修改的用户
     */
    User updateUser(UserInVO userInVO);

    /**
     * 增加用户信息
     * @param userInVO              用户
     * @return                  增加的用户
     */
    User newUser(UserInVO userInVO);
    /**
     * 删除用户信息（需求更改预留接口）
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

    /**
     * 模糊查询用户
     * @param userRole                  用户角色
     * @param searchKey                 用户名
     * @param page                      当前页数
     * @param pageSize                  页数大小
     * @return
     */
    Page<User> searchUserName(String userRole,String searchKey, int page, int pageSize);

}
