package com.manageplat.task.dao;

import cn.gfire.base.jpa.dao.BaseRepository;
import com.manageplat.task.domain.TaskRecord;
import org.springframework.stereotype.Repository;

/**
 * Created by CTY on 2018/8/16/016.
 */
@Repository
public interface TaskRecordDao extends BaseRepository<TaskRecord,Long> {
}
