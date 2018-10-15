package com.manageplat.admin.controller;

import cn.gfire.base.jpa.dao.query.BaseQuery;
import cn.gfire.base.mvc.controller.CrudController;
import cn.gfire.base.mvc.form.BaseDataResponse;
import cn.gfire.base.mvc.form.BaseForm;
import com.manageplat.admin.domain.AdminUser;
import com.manageplat.admin.domain.Permission;
import com.manageplat.admin.domain.Role;
import com.manageplat.admin.form.RoleForm;
import com.manageplat.admin.query.RoleQuery;
import com.manageplat.admin.service.AdminUserService;
import com.manageplat.admin.service.PermissionService;
import com.manageplat.admin.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CTY on 2018/8/13/013.
 */
@Controller
@RequestMapping("role")
public class RoleController extends CrudController<Role,Long> {

    private String LIST_URL="/role/list";

    private String ADD_URL="role/add";

    @Resource
    private PermissionService permissionService;

    @Resource
    private AdminUserService adminUserService;



    @GetMapping("edit")
    public String edit(Model model,Long id){
        Role role = service.findOne(id).get();
        model.addAttribute("form",role);
        setBaseData(model, permissionService.findAll());
        return getFormUrl();
    }



    @RequestMapping("list")
    public  String list(Model model, RoleQuery query) {
        return super.list(model, query);
    }

    @GetMapping("add")
    public String add(Model model) {
        setBaseData(model, permissionService.findAll());
        model.addAttribute("form",getBlankForm());
        return ADD_URL;
    }

    @PostMapping("add")
    @ResponseBody
    public BaseDataResponse add(RoleForm form) {
        ((RoleService) service).saveRolePerm(form.as(),form.getPermissions());
        return BaseDataResponse.ok().jumpUrl("/role/list");
    }

    @RequestMapping("delete")
    @ResponseBody
    public BaseDataResponse delete(Long id){
        Role role = service.findOne(id).get();
        List<Permission> permissions = role.getPermissions();
        for (Permission permission:permissions){
            List<Role> roles = permission.getRoles();
            roles.remove(role);
            permission.setRoles(roles);
            permissionService.save(permission);
        }
        List<AdminUser> adminUsers = role.getAdminUsers();
        for(AdminUser adminUser:adminUsers){
            adminUser.setRole(null);
            adminUserService.save(adminUser);
        }
        service.delete(role);
        return BaseDataResponse.ok();
    }


    private void setBaseData(Model model, List<Permission> permissions) {
        model.addAttribute("perms", permissions);
        Map<String, List<Permission>> stringListMap = new HashMap<>();
        permissions.forEach(tmp -> {
            if (stringListMap.get(tmp.getParentModeName()) != null) {
                stringListMap.get(tmp.getParentModeName()).add(tmp);
            } else {
                List<Permission> permissionList = new ArrayList<>();
                permissionList.add(tmp);
                stringListMap.put(tmp.getParentModeName(), permissionList);
            }
        });
        model.addAttribute("permissionMap", stringListMap);
    }

    @Override
    protected BaseForm<Role, Long> getBlankForm() {
        return new RoleForm();
    }

    @Override
    protected BaseForm<Role, Long> getForm(Role role) {
        return new RoleForm();
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
