package com.mabao.admin.controller.rest;

import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.controller.vo.UserVO;
import com.mabao.admin.enums.Role;
import com.mabao.admin.pojo.User;
import com.mabao.admin.service.UserService;
import com.mabao.admin.util.Selector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by lies on 2016/8/1.
 */
@RestController
@RequestMapping("/user")
public class UserRESTController {
    @Autowired
    private UserService userService;

    /**
     *显示所有用户信息
     * @param model         map
     * @return              账户管理
     */
    @RequestMapping(value = "/list", method = GET)
    public JsonResultVO list(int page, int pageSize,Model model) {
        try{
            Map<String,Object> map = new HashMap<>();
            //页面显示的用户
            Page<User> userList = this.userService.getAllUser(page,pageSize);
            map.put("goodsType",userList);
            //用户角色
            List<Selector> role = Role.toList();
            map.put("role",role);
            model.addAllAttributes(map);
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"显示成功！");
    }

    /**
     * 选择商品并删除
     * @param userIds 选择的商品ids
     * @return
     */
    @RequestMapping(value = "/deleteSomeGoods", method = GET)
    public JsonResultVO deleteUser(@RequestParam String userIds) {
        try{
            String[] cartArray = userIds.trim().split(",");
            for (String one : cartArray) {
                //获得购物车ID
                Long cartId = Long.valueOf(one);
                //查找商品
                this.userService.deleteUser(cartId);
            }
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"成功！");
    }

    @RequestMapping(value = "/addUser", method = GET)
    public JsonResultVO addUser(UserVO userVO) {
        try{

        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"成功！");
    }


}
