package com.mabao.admin.controller;

import com.mabao.admin.controller.vo.UserVO;
import com.mabao.admin.enums.Role;
import com.mabao.admin.pojo.User;
import com.mabao.admin.service.UserService;
import com.mabao.admin.util.Selector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by lies on 2016/7/28.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     *
     * @param model         map
     * @return              账户管理
     */
    @RequestMapping(value = "/list", method = GET)
    public String list(Model model) {
        Map<String,Object> map = new HashMap<>();
        //商品类型
        List<User> userList = this.userService.getAllUser();
        map.put("goodsType",userList);
        //宝宝性别
        List<Selector> role = Role.toList();
        map.put("role",role);

        model.addAllAttributes(map);
        return "account_management";
    }

    /**
     * 选择商品并删除
     *
     * @param userIds 选择的商品ids
     * @return
     */
    @RequestMapping(value = "/deleteGoods", method = GET)
    public String deleteUser(@RequestParam String userIds) {
        String[] cartArray = userIds.trim().split(",");
        for (String one : cartArray) {
            //获得购物车ID
            Long cartId = Long.valueOf(one);
            //查找商品
            this.userService.deleteUser(cartId);
        }
        return "list";
    }

    @RequestMapping(value = "/addUser", method = GET)
    public String addUser(UserVO userVO) {

        return "list";
    }


}
