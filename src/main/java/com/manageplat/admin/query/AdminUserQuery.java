package com.manageplat.admin.query;

import cn.gfire.base.jpa.dao.query.BaseWordQuery;
import cn.gfire.base.jpa.dao.query.MatchType;
import cn.gfire.base.jpa.dao.query.QueryWord;
import com.manageplat.admin.domain.AdminUser;

/**
 * @author wust
 * @create 2018-04-03 10:41
 * DESC
 **/
public class AdminUserQuery extends BaseWordQuery<AdminUser> {

     @QueryWord(matchType = MatchType.eq,value = "department.id")
    private Long department;

     @QueryWord(matchType = MatchType.eq,value = "post.id")
    private Long post;

    public Long getPost() {
        return post;
    }

    public void setPost(Long post) {
        this.post = post;
    }

    public Long getDepartment() {
        return department;
    }

    public void setDepartment(Long department) {
        this.department = department;
    }
}
