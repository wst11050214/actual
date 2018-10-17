package com.manageplat.category.query;

import cn.gfire.base.jpa.dao.query.BaseWordQuery;
import cn.gfire.base.jpa.dao.query.MatchType;
import cn.gfire.base.jpa.dao.query.QueryWord;
import com.manageplat.category.domain.GoodsCategory;

/**
 * Created by CTY on 2018/10/15/015.
 */
public class GoodsCategoryQuery extends BaseWordQuery<GoodsCategory> {

    @QueryWord(matchType = MatchType.eq)
    private String cateName;


    @QueryWord(matchType = MatchType.eq)
    private Integer cateClass;

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public Integer getCateClass() {
        return cateClass;
    }

    public void setCateClass(Integer cateClass) {
        this.cateClass = cateClass;
    }
}
