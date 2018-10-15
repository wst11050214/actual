package com.manageplat.dept.dao;

import cn.gfire.base.jpa.dao.BaseRepository;
import com.manageplat.dept.domain.Department;
import org.springframework.stereotype.Repository;

/**
 * Created by CTY on 2018/8/9/009.
 */
@Repository
public interface DepartmentDao extends BaseRepository<Department,Long> {
}
