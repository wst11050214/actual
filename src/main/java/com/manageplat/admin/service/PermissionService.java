package com.manageplat.admin.service;

import cn.gfire.base.jpa.service.BaseService;
import com.manageplat.admin.domain.Permission;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CTY on 2018/8/13/013.
 */
@Service
public class PermissionService extends BaseService<Permission,Long> {

    //验证是否包含指定权限
    public Boolean checkIncludePerm(List<Permission> permissions, String chekPremisson) {
        if(permissions!=null){
           for(Permission permission:permissions){
               if(chekPremisson.equals(permission.getModCode())){
                  return true;
               }
           }
        }
        return false;
    }
}
