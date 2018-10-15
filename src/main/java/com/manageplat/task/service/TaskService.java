package com.manageplat.task.service;

import cn.gfire.base.jpa.service.BaseService;
import com.manageplat.task.dao.TaskDao;
import com.manageplat.task.domain.Task;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CTY on 2018/8/16/016.
 */
@Service
public class TaskService extends BaseService<Task,Long> {

    public List<Task> queryUserTask(Long userId){
       return  ((TaskDao)dao).queryUserTask(userId);
    }


    public void deleteTaskUser(Long taskId){
        ((TaskDao)dao).deleteTaskAndUser(taskId);
    }
}
