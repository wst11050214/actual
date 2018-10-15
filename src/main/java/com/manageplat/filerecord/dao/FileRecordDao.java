package com.manageplat.filerecord.dao;

import cn.gfire.base.jpa.dao.BaseRepository;
import com.manageplat.filerecord.domain.FileRecord;
import org.springframework.stereotype.Repository;

/**
 * Created by CTY on 2018/9/5/005.
 */
@Repository
public interface FileRecordDao extends BaseRepository<FileRecord,Long>{
}
