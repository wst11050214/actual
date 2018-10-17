package com.manageplat.category.dao;

import cn.gfire.base.jpa.dao.BaseRepository;
import com.manageplat.category.domain.GoodsCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by CTY on 2018/10/15/015.
 */
@Repository
public interface GoodsCategoryDao extends BaseRepository<GoodsCategory,Long> {
    List<GoodsCategory> findByCateClass(Integer cateClass);
}
