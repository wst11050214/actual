package com.manageplat.task.domain;

import cn.gfire.base.jpa.domain.BaseDomain;
import cn.gfire.base.jpa.domain.DomainName;
import com.manageplat.admin.domain.AdminUser;
import org.hibernate.cfg.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by CTY on 2018/8/16/016.
 */
@DomainName("任务操作记录")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class TaskRecord extends BaseDomain<Long> {

    @Comment("记录内容自动生成")
    private String recordTitle;

    @Comment("记录内容")
    @Column(columnDefinition = "TEXT")
    private String recordContent;

    @CreatedDate
    private Date createDate;

    @Comment("操作用户")
    @ManyToOne
    private AdminUser adminUser;

    @Comment("对应的任务")
    @ManyToOne
    private Task task;

    @Comment("推送打分状态 0 未推送 1 已推送")
    private Integer pushState=0;

    public String getRecordTitle() {
        return recordTitle;
    }

    public void setRecordTitle(String recordTitle) {
        this.recordTitle = recordTitle;
    }

    public Integer getPushState() {
        return pushState;
    }

    public void setPushState(Integer pushState) {
        this.pushState = pushState;
    }

    public String getRecordContent() {
        return recordContent;
    }

    public void setRecordContent(String recordContent) {
        this.recordContent = recordContent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public AdminUser getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
