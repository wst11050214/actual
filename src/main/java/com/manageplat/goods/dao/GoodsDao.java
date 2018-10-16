package com.manageplat.goods.dao;

import cn.gfire.base.jpa.dao.BaseRepository;
import com.manageplat.goods.domain.Goods;
import org.springframework.stereotype.Repository;

/**
 * Created by CTY on 2018/10/16/016.
 */
@Repository
public interface GoodsDao extends BaseRepository<Goods,Long> {
}
