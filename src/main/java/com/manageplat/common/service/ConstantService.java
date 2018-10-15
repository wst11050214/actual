package com.manageplat.common.service;

import cn.gfire.base.jpa.service.BaseService;
import com.manageplat.common.dao.ConstantDao;
import com.manageplat.common.domain.Constant;
import org.springframework.stereotype.Service;

/**
 * Created by CTY on 2018/8/9/009.
 */
@Service
public class ConstantService extends BaseService<Constant,Long> {


    public Constant findByName(String constName){
        return ((ConstantDao)dao).findByConstName(constName);
    }
}
