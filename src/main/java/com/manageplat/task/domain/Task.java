package com.manageplat.task.domain;

import cn.gfire.base.jpa.domain.BaseDomain;
import cn.gfire.base.jpa.domain.DomainName;
import com.manageplat.admin.domain.AdminUser;
import com.manageplat.filerecord.domain.FileRecord;
import org.hibernate.cfg.annotations.Comment;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by CTY on 2018/8/16/016.
 *
 */
@DomainName("任务")
@Entity
@EntityListeners({AuditingEntityListener.class})
public class Task extends BaseDomain<Long> {

    @Comment("任务主题")
    private String taskTitle;

    @Comment("任务内容")
    @Column(
            columnDefinition = "TEXT"
    )
    private String taskContent;

    @Comment("任务进度 0-100")
    private Integer progress;

    @Comment("任务状态 1进行中 2 取消 3 完成")
    private Integer taskState;

    @Comment("任务负责人")
    @OneToOne
    private AdminUser taskLeader;

    @Comment("任务附件")
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<FileRecord> fileRecords;

    @ManyToMany(mappedBy = "tasks",cascade = CascadeType.REMOVE)
    private List<AdminUser> adminUserList=new ArrayList<>();


    @Comment("任务结束时间")
    private Date finishDate;

    @CreatedDate
    @Column(
            updatable = false
    )
    private Date createDate;

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public List<FileRecord> getFileRecords() {
        return fileRecords;
    }

    public void setFileRecords(List<FileRecord> fileRecords) {
        this.fileRecords = fileRecords;
    }

    public AdminUser getTaskLeader() {
        return taskLeader;
    }

    public void setTaskLeader(AdminUser taskLeader) {
        this.taskLeader = taskLeader;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<AdminUser> getAdminUserList() {
        return adminUserList;
    }

    public void setAdminUserList(List<AdminUser> adminUserList) {
        this.adminUserList = adminUserList;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getTaskState() {
        return taskState;
    }

    public void setTaskState(Integer taskState) {
        this.taskState = taskState;
    }

}
