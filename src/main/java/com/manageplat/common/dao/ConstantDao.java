package com.manageplat.common.dao;

import cn.gfire.base.jpa.dao.BaseRepository;
import com.manageplat.common.domain.Constant;
import org.springframework.stereotype.Repository;

/**
 * Created by CTY on 2018/8/9/009.
 */
@Repository
public interface ConstantDao extends BaseRepository<Constant,Long> {

    public Constant findByConstName(String constName);
}
