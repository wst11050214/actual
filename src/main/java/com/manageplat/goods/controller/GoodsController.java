package com.manageplat.goods.controller;

import cn.gfire.base.mvc.controller.CrudController;
import cn.gfire.base.mvc.form.BaseForm;
import com.manageplat.goods.domain.Goods;
import com.manageplat.goods.from.GoodsFrom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by CTY on 2018/10/16/016.
 */
@RequestMapping("goods")
@Controller
public class GoodsController extends CrudController<Goods,Long> {

    private String LIST_URL="goods/list";

    private String ADD_URL="goods/add";

    @Override
    protected BaseForm<Goods, Long> getBlankForm() {

      return new GoodsFrom();
    }
    @Override
    protected BaseForm<Goods, Long> getForm(Goods goods) {
        return new GoodsFrom();
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
