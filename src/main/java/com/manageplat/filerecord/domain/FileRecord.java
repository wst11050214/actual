package com.manageplat.filerecord.domain;

import cn.gfire.base.jpa.domain.BaseDomain;
import org.hibernate.cfg.annotations.Comment;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.util.Date;

/**
 * Created by CTY on 2018/9/5/005.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class FileRecord extends BaseDomain<Long>{

    private String fileName;

    @Comment("1 任务附件")
    private Integer type;

    @CreatedDate
    private Date createDate;

    @Comment("文件类型")
    private String suffix;

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
