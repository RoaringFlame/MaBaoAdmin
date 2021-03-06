package com.mabao.admin.controller.rest;

import com.mabao.admin.controller.vo.JsonResultVO;
import com.mabao.admin.controller.vo.UserInVO;
import com.mabao.admin.controller.vo.UserVO;
import com.mabao.admin.pojo.User;
import com.mabao.admin.service.UserService;
import com.mabao.admin.util.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
     * 模糊查询用户
     * @param userRole                  用户角色
     * @param searchKey                 用户名
     * @param page                      当前页数
     * @param pageSize                  页数大小
     * @return
     */
    @RequestMapping(value = "/searchUserName",method = RequestMethod.GET)
    public PageVO<UserVO> searchGoodsType(@RequestParam(required = false ,defaultValue = "") String userRole,
                                          @RequestParam(required = false ,defaultValue = "") String searchKey,
                                          @RequestParam(required = false ,defaultValue = "1") int page,
                                          @RequestParam(required = false ,defaultValue = "8") int pageSize) {
        Page<User> pageUser =this.userService.searchUserName(userRole,searchKey,page,pageSize);
        PageVO<UserVO> voPage = new PageVO<>();
        voPage.toPage(pageUser);
        voPage.setItems(UserVO.generateBy(pageUser.getContent()));
        return voPage;
    }


    /**
     * 选择用户并删除
     * @param userIds           选择的用户ids
     * @return
     */
    @RequestMapping(value = "/deleteSomeUser", method = GET)
    public JsonResultVO deleteUser(String userIds) {
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
    public JsonResultVO addUser(@RequestBody UserInVO userInVO) {
        try{
            this.userService.newUser(userInVO);
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"添加成功！");
    }

    /**
     * 修改用户
     * @param userInVO
     * @return
     */
    @RequestMapping(value = "/updateUser", method = POST)
    public JsonResultVO updateUser(UserInVO userInVO) {
        try{
            this.userService.updateUser(userInVO);
        }catch (Exception e){
            return new JsonResultVO(JsonResultVO.FAILURE,e.getMessage());
        }
        return new JsonResultVO(JsonResultVO.SUCCESS,"修改成功！");
    }

    /**
     * 修改时用户信息回现
     * @param userId            用户id
     * @return
     */
    @RequestMapping(value = "/getUser", method = GET)
    public UserInVO getUser(@RequestParam Long userId) {
        return UserInVO.generateBy(this.userService.get(userId));
    }
}
