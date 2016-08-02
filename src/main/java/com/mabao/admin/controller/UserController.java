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

}
