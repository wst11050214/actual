package com.manageplat.common.domain;

import cn.gfire.base.jpa.domain.BaseDomain;
import org.hibernate.cfg.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by CTY on 2018/8/9/009.
 * 定义系统参数
 */
@Entity
public class Constant extends BaseDomain<Long> {

    @Comment("参数名称 保证唯一")
    @Column(unique = true)
    private String constName;

    @Comment("参数值")
    private String constValue;

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName = constName;
    }

    public String getConstValue() {
        return constValue;
    }

    public void setConstValue(String constValue) {
        this.constValue = constValue;
    }
}
