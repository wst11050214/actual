package com.manageplat.category.domain;

import cn.gfire.base.jpa.domain.BaseDomain;
import cn.gfire.base.jpa.domain.DomainName;
import org.hibernate.cfg.annotations.Comment;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Locale;

/**
 * Created by CTY on 2018/10/15/015.
 */
@DomainName("商品分类")
@Entity
public class GoodsCategory extends BaseDomain<Long> {

    private String cateName;

    @ManyToOne
    private GoodsCategory supCate;

    @Comment("分类等级")
    private Integer cateClass;

    private String cateIcon;

    @Comment("分类状态 1 正常 0 停用")
    private Integer cateState=1;

    public Integer getCateState() {
        return cateState;
    }

    public void setCateState(Integer cateState) {
        this.cateState = cateState;
    }

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
}
