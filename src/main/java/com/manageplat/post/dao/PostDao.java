package com.manageplat.post.dao;

import cn.gfire.base.jpa.dao.BaseRepository;
import com.manageplat.post.domain.Post;
import org.springframework.stereotype.Repository;

/**
 * Created by CTY on 2018/8/27/027.
 */
@Repository
public interface PostDao extends BaseRepository<Post,Long> {
}
