package com.manageplat.empmap.controller;

import cn.gfire.base.mvc.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by CTY on 2018/9/21/021.
 */
@RequestMapping("empMap")
@Controller
public class EmpMapController extends BaseController {

    /**
     * 展示员工实时地图
     * @return
     */
    @RequestMapping("showEmpMap")
    public String showEmpMap(){

        return "empmap/showmap";
    }
}
