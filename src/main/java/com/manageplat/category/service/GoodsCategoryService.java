package com.manageplat.category.service;

import cn.gfire.base.jpa.service.BaseService;
import com.manageplat.category.dao.GoodsCategoryDao;
import com.manageplat.category.domain.GoodsCategory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CTY on 2018/10/15/015.
 */
@Service
public class GoodsCategoryService extends BaseService<GoodsCategory,Long> {
    public List<GoodsCategory> findbyClass(Integer cateClass) {
        return ((GoodsCategoryDao)dao).findByCateClass(cateClass);
    }
}
