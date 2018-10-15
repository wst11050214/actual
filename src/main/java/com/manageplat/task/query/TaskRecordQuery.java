package com.manageplat.task.query;

import cn.gfire.base.jpa.dao.query.BaseWordQuery;
import cn.gfire.base.jpa.dao.query.MatchType;
import cn.gfire.base.jpa.dao.query.QueryWord;
import com.manageplat.task.domain.TaskRecord;

/**
 * Created by CTY on 2018/8/16/016.
 */
public class TaskRecordQuery extends BaseWordQuery<TaskRecord> {


    @QueryWord(matchType = MatchType.eq,value = "task.id")
    private Long task;

    @QueryWord(matchType = MatchType.eq,value = "adminUser.id")
    private Long adminUser;

    public Long getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(Long adminUser) {
        this.adminUser = adminUser;
    }

    public Long getTask() {
        return task;
    }

    public void setTask(Long task) {
        this.task = task;
    }
}
