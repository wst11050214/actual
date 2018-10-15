package com.manageplat.post.domain;

import cn.gfire.base.jpa.domain.BaseDomain;
import cn.gfire.base.jpa.domain.DomainName;
import org.hibernate.cfg.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by CTY on 2018/8/27/027.
 */
@Entity
@DomainName("岗位")
public class Post extends BaseDomain<Long> {

    @Comment("岗位名称")
    private String postName;

    @Comment("岗位描述")
    @Column(columnDefinition = "TEXT")
    private String postDesc;

    @Comment("岗位分")
    private Integer postScore;

    public Integer getPostScore() {
        return postScore;
    }

    public void setPostScore(Integer postScore) {
        this.postScore = postScore;
    }

    public String getPostDesc() {
        return postDesc;
    }

    public void setPostDesc(String postDesc) {
        this.postDesc = postDesc;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }
}
