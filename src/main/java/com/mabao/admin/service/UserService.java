package com.mabao.admin.service;


import com.mabao.admin.controller.vo.UserInVO;
import com.mabao.admin.pojo.GoodsType;
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
     * @param searchKey                 搜索关键字
     * @param page                      页码
     * @param pageSize                  每页数量
     * @return                         分页Page<User>
     */
    Page<User> searchUserName(String searchKey, int page, int pageSize);

}
