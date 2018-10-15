package com.manageplat.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wust
 * @create 2018-04-03 9:37
 * DESC
 **/
@Controller
@RequestMapping("index")
public class IndexController {

    @RequestMapping("")
    public String index(){
        return "admin/login";
    }

    @RequestMapping("home")
    public String index(Model model){
        return "admin/index";
    }

    @RequestMapping("body")
    public String body(Model model){
        return "admin/body";
    }

}
