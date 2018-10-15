package com.manageplat.task.query;

import cn.gfire.base.jpa.dao.query.BaseWordQuery;
import cn.gfire.base.jpa.dao.query.MatchType;
import cn.gfire.base.jpa.dao.query.QueryWord;
import com.manageplat.task.domain.Task;

/**
 * Created by CTY on 2018/8/16/016.
 */
public class TaskQuery extends BaseWordQuery<Task> {

    @QueryWord(matchType = MatchType.eq,value = "taskLeader.id")
    private Long taskLeader;

    @QueryWord(matchType = MatchType.eq)
    private Integer taskState;

    public Integer getTaskState() {
        return taskState;
    }

    public void setTaskState(Integer taskState) {
        this.taskState = taskState;
    }

    public Long getTaskLeader() {
        return taskLeader;
    }

    public void setTaskLeader(Long taskLeader) {
        this.taskLeader = taskLeader;
    }
}
