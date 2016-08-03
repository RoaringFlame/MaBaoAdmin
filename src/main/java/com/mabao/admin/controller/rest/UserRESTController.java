package com.mabao.admin.controller.rest;

import com.mabao.admin.controller.vo.*;
import com.mabao.admin.enums.Role;
import com.mabao.admin.pojo.Goods;
import com.mabao.admin.pojo.GoodsType;
import com.mabao.admin.pojo.User;
import com.mabao.admin.service.UserService;
import com.mabao.admin.util.PageVO;
import com.mabao.admin.util.Selector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by lies on 2016/8/1.
 */
@RestController
@RequestMapping("/user")
public class UserRESTController {
    @Autowired
    private UserService userService;

    /**
     * 分页显示所有信息
     * @param page              分页参数
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/list", method = GET)
    public PageVO<UserVO> list(int page, int pageSize) {
        Page<User> userList = this.userService.getAllUser(page,pageSize);
        PageVO<UserVO> voPage = new PageVO<>();
        voPage.toPage(userList);
        voPage.setItems(UserVO.generateBy(userList.getContent()));
        return voPage;
    }

    /**
     * 模糊查询用户
     * @param searchKey                 搜索关键字
     * @param page                      页码
     * @param pageSize                  每页数量
     * @return                          分页GoodsTypeVO
     */
    @RequestMapping(value = "/searchUserName",method = RequestMethod.GET)
    public PageVO<UserVO> searchGoodsType(@RequestParam(required = false ,defaultValue = "") String searchKey,
                                               @RequestParam(required = false ,defaultValue = "1") int page,
                                               @RequestParam(required = false ,defaultValue = "8") int pageSize) {
        Page<User> pageUser =this.userService.searchUserName(searchKey,page,pageSize);
        PageVO<UserVO> voPage = new PageVO<>();
        voPage.toPage(pageUser);
        voPage.setItems(UserVO.generateBy(pageUser.getContent()));
        return voPage;
    }

    /**
     * 显示页面下拉框
     * @return              用户角色信息列表
     */
    @RequestMapping(method = GET)
    public List<Selector> initUser() {
        return Role.toList();
    }

    /**
     * 选择用户并删除
     * @param userIds 选择的商品ids
     * @return
     */
    @RequestMapping(value = "/deleteSomeUser", method = GET)
    public JsonResultVO deleteUser(@RequestParam String userIds) {
        try{
            this.userService.deleteSomeUser(userIds);
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"删除成功！");
    }

    /**
     * 添加用户
     * @param userInVO
     * @return
     */
    @RequestMapping(value = "/addUser", method = POST)
    public JsonResultVO addUser(UserInVO userInVO) {
        try{
            this.userService.newUser(userInVO);
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"添加成功！");
    }

}
