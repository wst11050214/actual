package com.manageplat.admin.form;

import cn.gfire.base.mvc.form.BaseForm;
import com.manageplat.admin.domain.Role;

/**
 * Created by CTY on 2018/8/13/013.
 */
public class RoleForm extends BaseForm<Role,Long> {

    private String roleName;

    private Long[]  permissions;


    public Long[] getPermissions() {
        return permissions;
    }

    public void setPermissions(Long[] permissions) {
        this.permissions = permissions;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getName() {
        return null;
    }
}
