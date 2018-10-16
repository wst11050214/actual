package com.manageplat.goods.domain;

import cn.gfire.base.jpa.domain.BaseDomain;
import cn.gfire.base.jpa.domain.DomainName;
import org.hibernate.cfg.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by CTY on 2018/10/15/015.
 */
@Entity
@DomainName("商品表")
public class Goods extends BaseDomain<Long>{

    private String title;

    private String desc;

    @Comment("商品详情页")
    @Column(columnDefinition = "TEXT")
    private String  goodsDetail;

    @Comment("分类名称")
    private String cateName;

    @Comment("所属分类")
    private Long cateId;

    @Comment("价格")
    private Double price;

    @Comment("活动价格")
    private Double activePrice=0.0;

    @Comment("活动ID")
    private Long activeId;

    @Comment("计量单位")
    private String uintSale;

    @Comment("数量")
    private String saleCountUnit;

    public String getSaleCountUnit() {
        return saleCountUnit;
    }

    public void setSaleCountUnit(String saleCountUnit) {
        this.saleCountUnit = saleCountUnit;
    }

    public String getUintSale() {
        return uintSale;
    }

    public void setUintSale(String uintSale) {
        this.uintSale = uintSale;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public Long getCateId() {
        return cateId;
    }

    public void setCateId(Long cateId) {
        this.cateId = cateId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getActivePrice() {
        return activePrice;
    }

    public void setActivePrice(Double activePrice) {
        this.activePrice = activePrice;
    }

    public Long getActiveId() {
        return activeId;
    }

    public void setActiveId(Long activeId) {
        this.activeId = activeId;
    }
}
