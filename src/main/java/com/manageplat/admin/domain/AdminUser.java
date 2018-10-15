package com.manageplat.admin.domain;

import cn.gfire.base.jpa.domain.BaseDomain;
import com.manageplat.dept.domain.Department;
import com.manageplat.post.domain.Post;
import com.manageplat.task.domain.Task;
import org.hibernate.cfg.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wust
 * @create 2018-04-03 9:34
 * DESC  管理员登陆的
 **/
@Entity
public class AdminUser extends BaseDomain<Long> {

    private String loginName;

    private String passWord;

    private String phoneNo;

    @Comment("用户状态 1 正常 0 冻结")
    private Integer state;

    @Comment("员工姓名")
    private String userName;

    @Comment("自我介绍")
    @Column(columnDefinition = "TEXT")
    private String userDesc;

    @Comment("邮箱")
    private String email;

    @Comment("用户头像")
    private String userPhoto;

    @Comment("职务")
    private String job;

    @ManyToOne
    private Role role;

    @Comment("所属部门")
    @ManyToOne
    private Department department;

    @ManyToOne
    private Post post;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Comment("对应的任务")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = {@JoinColumn(
            referencedColumnName = "id"
    )},
            inverseJoinColumns = {@JoinColumn(
                    referencedColumnName = "id"
            )}
    )


    private List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
}
