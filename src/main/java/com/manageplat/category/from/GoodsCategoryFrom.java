package com.manageplat.category.from;

import cn.gfire.base.mvc.form.BaseForm;
import com.manageplat.category.domain.GoodsCategory;

/**
 * Created by CTY on 2018/10/15/015.
 */
public class GoodsCategoryFrom extends BaseForm<GoodsCategory,Long> {

    private String cateName;

    private GoodsCategory supCate;

    private Integer cateClass;

    private String cateIcon;

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public GoodsCategory getSupCate() {
        return supCate;
    }

    public void setSupCate(GoodsCategory supCate) {
        this.supCate = supCate;
    }

    public Integer getCateClass() {
        return cateClass;
    }

    public void setCateClass(Integer cateClass) {
        this.cateClass = cateClass;
    }

    public String getCateIcon() {
        return cateIcon;
    }

    public void setCateIcon(String cateIcon) {
        this.cateIcon = cateIcon;
    }

    @Override
    public String getName() {
        return null;
    }
}
