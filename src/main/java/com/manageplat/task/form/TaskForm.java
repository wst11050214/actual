package com.manageplat.task.form;

import cn.gfire.base.mvc.form.BaseForm;
import com.manageplat.admin.domain.AdminUser;
import com.manageplat.task.domain.Task;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by CTY on 2018/8/16/016.
 */
public class TaskForm extends BaseForm<Task,Long> {

    @NotEmpty(message = "任务主题不能为空")
    private String taskTitle;

    @NotEmpty(message = "任务内容不能为空")
    private String taskContent;

    private Integer progress;

    private Integer taskState;

    private Long[] adminUsers;

    private AdminUser taskLeader;

    private String uploadFiles;

    private String  finishDate;

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getUploadFiles() {
        return uploadFiles;
    }

    public void setUploadFiles(String uploadFiles) {
        this.uploadFiles = uploadFiles;
    }

    public AdminUser getTaskLeader() {
        return taskLeader;
    }

    public void setTaskLeader(AdminUser taskLeader) {
        this.taskLeader = taskLeader;
    }

    public Long[] getAdminUsers() {
        return adminUsers;
    }

    public void setAdminUsers(Long[] adminUsers) {
        this.adminUsers = adminUsers;
    }

    public Integer getTaskState() {
        return taskState;
    }

    public void setTaskState(Integer taskState) {
        this.taskState = taskState;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
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

    @Override
    public String getName() {
        return null;
    }
}
