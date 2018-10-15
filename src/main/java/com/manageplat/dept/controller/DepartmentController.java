package com.manageplat.dept.controller;

import cn.gfire.base.jpa.dao.query.BaseQuery;
import cn.gfire.base.mvc.controller.CrudController;
import cn.gfire.base.mvc.form.BaseDataResponse;
import cn.gfire.base.mvc.form.BaseForm;
import com.alibaba.fastjson.JSONObject;
import com.country.utils.DateUtils;
import com.country.utils.UUIDGenerator;
import com.manageplat.common.domain.Constant;
import com.manageplat.common.service.ConstantService;
import com.manageplat.dept.domain.Department;
import com.manageplat.dept.form.DepartmentForm;
import com.manageplat.dept.query.DepartmentQuery;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CTY on 2018/8/9/009.
 */
@Controller
@RequestMapping("dept")
@ConfigurationProperties(prefix = "com.imdoa")
public class DepartmentController extends CrudController<Department,Long> {

    private String LIST_URL="dept/list";
    private String FORM_URL="dept/add";

    private String company;

    @Resource
    private ConstantService constantService;


    @RequestMapping("queryDeptByClass")
    @ResponseBody
    public BaseDataResponse queryDeptByClass(DepartmentQuery query){
        List<Department> content = service.findAll(query).getContent();
        List<Department> list = new ArrayList<>();
        for(Department department:content){
            department.setSubDepts(null);
            department.setParentDept(null);
            list.add(department);
        }
        return BaseDataResponse.ok().data(list);
    }


    @RequestMapping("galldept")
    public String gallDept(Model model,DepartmentQuery query){
        query.setDeptClass(1);
        query.setSize(50);
        List<Department> all = service.findAll(query).getContent();
        model.addAttribute("depts",all);
        Constant company = constantService.findByName("company");
        model.addAttribute("company",company.getConstValue());
        return "dept/gall";
    }


    @RequestMapping("edit")
    public String edit(Long id, Model model) {
        Department department = service.findOne(id).get();
        Constant company = constantService.findByName("company");
        model.addAttribute("company",company);
        List<Department> departments = service.findAll();
        model.addAttribute("depts",departments);
        model.addAttribute("form",department);

        return FORM_URL;

    }

    @GetMapping("add")
    public String add(Model model) {
        Constant company = constantService.findByName("company");
        model.addAttribute("company",company);
        List<Department> departments = service.findAll();
//        model.addAttribute("depts",departments);
        return super.add(model);
    }

    @PostMapping(value = "add")
    @ResponseBody
    public BaseDataResponse add(@RequestBody @Valid DepartmentForm form, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return BaseDataResponse.verificationFail(bindingResult);
        }
        Department department = form.as();
        if(form.getDeptClass()!=1&&form.getParentDept()==null){
            return BaseDataResponse.fail().msg("请选择上级部门");
        }
        Department parentDept=null;
        if(form.getParentDept()!=null){
            parentDept = service.findOne(form.getParentDept()).get();
            department.setParentDept(parentDept);
        }
        Department dept = service.save(department).get();
        dept.setDeptNo(DateUtils.getYear()+dept.getId());
        Department department1 = service.save(dept).get();
        if(parentDept!=null){
            List<Department> list=parentDept.getSubDepts()==null?new ArrayList<Department>():parentDept.getSubDepts();
            list.add(department1);
            parentDept.setSubDepts(list);
            service.save(parentDept);
        }
        return BaseDataResponse.ok().jumpUrl("/dept/list");
    }

    @RequestMapping("delete")
    @ResponseBody
    public BaseDataResponse delete(Long id) {
        DepartmentQuery query = new DepartmentQuery();
        query.setParentDept(id);
        List<Department> content = service.findAll(query).getContent();
        if(content!=null&&content.size()!=0){
            return BaseDataResponse.fail().msg("存在下级部门请先删除下级部门");
        }
        return super.delete(id);
    }

    @RequestMapping("list")
    public String list(Model model, DepartmentQuery query) {
        Constant company = constantService.findByName("company");
        model.addAttribute("company",company.getConstValue());
        return super.list(model, query);
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    protected DepartmentForm getBlankForm() {
        return new DepartmentForm();
    }

    @Override
    protected DepartmentForm getForm(Department department) {
        return new DepartmentForm();
    }

    @Override
    protected String getListUrl() {
        return LIST_URL;
    }

    @Override
    protected String getFormUrl() {
        return FORM_URL;
    }
}
