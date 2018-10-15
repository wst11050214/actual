package com.manageplat.admin.service;

import cn.gfire.base.jpa.service.BaseService;
import com.manageplat.admin.dao.PermissionDao;
import com.manageplat.admin.domain.Permission;
import com.manageplat.admin.domain.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CTY on 2018/8/13/013.
 */
@Service
public class RoleService extends BaseService<Role,Long> {

    @Resource
    private PermissionDao permissionDao;

    public void saveRolePerm(Role role, Long[] permissions) {
        List<Permission> list = new ArrayList<>();
        for(Long perId:permissions){
            Permission one = permissionDao.findOne(perId);
            list.add(one);
        }
        role.setPermissions(list);

        save(role);
    }
}
