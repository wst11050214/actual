package com.manageplat.category.controller;

import cn.gfire.base.jpa.dao.query.BaseQuery;
import cn.gfire.base.mvc.controller.CrudController;
import cn.gfire.base.mvc.form.BaseDataResponse;
import cn.gfire.base.mvc.form.BaseForm;
import com.manageplat.category.domain.GoodsCategory;
import com.manageplat.category.from.GoodsCategoryFrom;
import com.manageplat.category.query.GoodsCategoryQuery;
import com.manageplat.category.service.GoodsCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by CTY on 2018/10/15/015.
 */
@Controller
@RequestMapping("goodsCategory")
public class GoodsCategoryController extends CrudController<GoodsCategory,Long> {

    private String LIST_URL="goodscate/list";
    private String ADD_URL="goodscate/add";


    @RequestMapping("queryCateByClass")
    @ResponseBody
    public BaseDataResponse queryCateByClass(Integer cateClass){
        List<GoodsCategory> list =((GoodsCategoryService)service).findbyClass(cateClass);
        return BaseDataResponse.ok().data(list);
    }

    @GetMapping("add")
    public String add(Model model) {
        return super.add(model);
    }

    @PostMapping("add")
    @ResponseBody
    public BaseDataResponse add(GoodsCategoryFrom form, BindingResult bindingResult) {
        return super.add(form, bindingResult).jumpUrl("/goodsCategory/list");
    }

    @RequestMapping("list")
    public  String list(Model model, GoodsCategoryQuery query) {
        return super.list(model, query);
    }

    @RequestMapping("delete")
    @ResponseBody
    public BaseDataResponse delete(Long id) {
        try {
            super.delete(id);
        }catch (Exception e){
            return BaseDataResponse.fail().msg("删除失败，该分类被引用");
        }
        return BaseDataResponse.ok();
    }

    @Override
    protected BaseForm<GoodsCategory, Long> getBlankForm() {
        return new GoodsCategoryFrom();
    }

    @Override
    protected BaseForm<GoodsCategory, Long> getForm(GoodsCategory goodsCategory) {
        return new GoodsCategoryFrom();
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
