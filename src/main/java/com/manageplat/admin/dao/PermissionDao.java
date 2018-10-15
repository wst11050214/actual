package com.manageplat.admin.dao;

import cn.gfire.base.jpa.dao.BaseRepository;
import com.manageplat.admin.domain.Permission;
import org.springframework.stereotype.Repository;

/**
 * Created by CTY on 2018/4/17.
 */
@Repository
public interface PermissionDao extends BaseRepository<Permission,Long> {
}
