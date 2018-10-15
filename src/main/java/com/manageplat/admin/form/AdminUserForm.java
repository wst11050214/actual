package com.manageplat.admin.form;

import cn.gfire.base.mvc.form.BaseForm;
import com.manageplat.admin.domain.AdminUser;
import com.manageplat.post.domain.Post;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author wust
 * @create 2018-04-03 10:41
 * DESC
 **/
public class AdminUserForm extends BaseForm<AdminUser,Long> {

    @NotEmpty(message = "登录账号不能为空")
    private String loginName;

    @NotEmpty(message = "密码不能为空")
    private String passWord;

    @NotEmpty(message = "电话号码不能为空")
    private String phoneNo;

    private Integer state;

    @NotEmpty(message = "用户姓名不能为空")
    private String userName;

    private Long role;

    private Long department;

    private Long post;

    public Long getPost() {
        return post;
    }

    public void setPost(Long post) {
        this.post = post;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public Long getDepartment() {
        return department;
    }

    public void setDepartment(Long department) {
        this.department = department;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getName() {
        return null;
    }
}
