package com.mabao.admin.controller.rest;

import com.mabao.admin.controller.vo.GoodsVO;
import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.controller.vo.UserVO;
import com.mabao.admin.enums.Role;
import com.mabao.admin.pojo.Goods;
import com.mabao.admin.pojo.User;
import com.mabao.admin.service.UserService;
import com.mabao.admin.util.PageVO;
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
     * 分页显示所有信息
     * @param page              分页参数
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/list", method = GET)
    public PageVO<UserVO> list(int page, int pageSize) {
        Page<User> goodsList = this.userService.getAllUser(page,pageSize);
        PageVO<UserVO> voPage = new PageVO<>();
        voPage.toPage(goodsList);
//        voPage.setItems(UserVO.generateBy(goodsList.getContent()));
        return voPage;
    }

    /**
     * 显示用户信息
     * @return              用户角色信息列表
     */
    @RequestMapping( method = GET)
    public List<Selector> initUser() {
        return Role.toList();
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

    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/addUser", method = GET)
    public JsonResultVO addUser(User user) {
        try{
            this.userService.newUser(user);
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"成功！");
    }


}
