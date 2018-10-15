package com.manageplat.common.controller;

import cn.gfire.base.mvc.controller.CrudController;
import com.manageplat.common.domain.Constant;
import com.manageplat.common.form.ConstantForm;
import com.manageplat.common.query.ConstantQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by CTY on 2018/8/23/023.
 */
@Controller
@RequestMapping("constant")
public class ConstantController extends CrudController<Constant,Long> {


     private String LIST_URL="system/list";

     private String ADD_URL="system/add";


    @RequestMapping("list")
    public String list(Model model, ConstantQuery query) {
        return super.list(model, query);
    }

    @Override
    protected ConstantForm getBlankForm() {
        return new ConstantForm();
    }

    @Override
    protected ConstantForm getForm(Constant constant) {
        return new ConstantForm();
    }

    @Override
    protected String getListUrl() {
        return LIST_URL;
    }

    @Override
    protected String getFormUrl() {
        return ADD_URL;
    }
}
