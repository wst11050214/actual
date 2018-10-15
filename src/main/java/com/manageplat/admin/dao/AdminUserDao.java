package com.manageplat.admin.dao;

import cn.gfire.base.jpa.dao.BaseRepository;
import com.manageplat.admin.domain.AdminUser;
import org.springframework.stereotype.Repository;

/**
 * Created by CTY on 2018/4/3.
 */
@Repository
public interface AdminUserDao extends BaseRepository<AdminUser,Long> {

    public AdminUser findByLoginName(String checkName);
}
