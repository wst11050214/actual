package com.manageplat.task.dao;

import cn.gfire.base.jpa.dao.BaseRepository;
import cn.gfire.base.jpa.dao.query.QueryWord;
import com.manageplat.task.domain.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by CTY on 2018/8/16/016.
 */
@Repository
public interface TaskDao extends BaseRepository<Task,Long> {


    /**
     * 查询 当前用户的任务
     * @param userId
     * @return
     */
    @Query(value = "SELECT * from task t,admin_user_tasks au where au.tasks_id=t.id and au.admin_user_list_id= :userId",nativeQuery = true)
    public List<Task> queryUserTask(@Param("userId") Long userId);


    @Query(value = "delete from admin_user_tasks where tasks_id= :taskId",nativeQuery = true)
    public void deleteTaskAndUser(@Param("taskId") Long taskId);
}
