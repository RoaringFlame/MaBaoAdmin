package com.mabao.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/")
public class HomeController {

    public HomeController() {
    }

    /**
     * jsp页面跳转控制器
     * @param jspPage  页面名
     * @return jspPage.jsp  页面名对应的jsp页面
     */
    @RequestMapping(value = "/jsp/{jspPage}", method = GET)
    public String defaultPage(@PathVariable("jspPage") String jspPage){
        return jspPage;
    }

}
