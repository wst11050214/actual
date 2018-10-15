package com.manageplat.post.form;

import cn.gfire.base.mvc.form.BaseForm;
import com.manageplat.post.domain.Post;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by CTY on 2018/8/27/027.
 */
public class PostForm extends BaseForm<Post,Long> {

    @NotEmpty(message = "岗位名称不能为空")
    private String postName;

    @NotEmpty(message = "岗位说明不能为空")
    private String postDesc;

    @NotEmpty(message = "岗位默认分不能为空")
    private Integer postScore;

    public Integer getPostScore() {
        return postScore;
    }

    public void setPostScore(Integer postScore) {
        this.postScore = postScore;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostDesc() {
        return postDesc;
    }

    public void setPostDesc(String postDesc) {
        this.postDesc = postDesc;
    }

    @Override
    public String getName() {
        return null;
    }
}
