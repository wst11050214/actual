package com.manageplat.admin.service;

import cn.gfire.base.jpa.service.BaseService;
import com.manageplat.admin.dao.AdminUserDao;
import com.manageplat.admin.domain.AdminUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wust
 * @create 2018-04-03 10:40
 * DESC
 **/
@Service
public class AdminUserService extends BaseService<AdminUser,Long> {

    public AdminUser findByLoginName(String checkName) {
        return ((AdminUserDao)dao).findByLoginName(checkName);
    }

    public List<AdminUser> queryAdminsByIds(Long[] adminUsers) {
        List<AdminUser> list = new ArrayList<>();
        if(adminUsers!=null&&adminUsers.length>0){
            for(Long id:adminUsers){
                AdminUser adminUser = findOne(id).get();
                list.add(adminUser);
            }
        }
        return list;
    }

    public AdminUser getCurrentUser(){
        AdminUser user = (AdminUser) SecurityUtils.getSubject().getPrincipal();
        return user;
    }
}
