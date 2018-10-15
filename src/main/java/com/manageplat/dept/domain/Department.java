package com.manageplat.dept.domain;

import cn.gfire.base.jpa.domain.BaseDomain;
import cn.gfire.base.jpa.domain.DomainName;
import com.manageplat.admin.domain.AdminUser;
import org.hibernate.cfg.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CTY on 2018/8/9/009.
 */
@Entity
@DomainName("部门")
public class Department extends BaseDomain<Long> {

    @Comment("部门名称")
    private String deptName;

    @Comment("部门编号 唯一")
    @Column(unique = true)
    private String deptNo;

    @Comment("部门等级 1顶级部门 2 二级部门 3 三级部门 4 四级部门")
    private Integer deptClass;


    @Comment("上级部门")
    @ManyToOne
    private Department parentDept;

    @Comment("下级部门")
     @OneToMany(mappedBy = "parentDept",fetch = FetchType.EAGER)
    private List<Department> subDepts = new ArrayList<>();


    public List<Department> getSubDepts() {
        return subDepts;
    }

    public void setSubDepts(List<Department> subDepts) {
        this.subDepts = subDepts;
    }

    public Integer getDeptClass() {
        return deptClass;
    }

    public void setDeptClass(Integer deptClass) {
        this.deptClass = deptClass;
    }

    //    @Comment("部门员工")
//    @OneToMany
//    private List<AdminUser> adminUsers;


    public Department getParentDept() {
        return parentDept;
    }

    public void setParentDept(Department parentDept) {
        this.parentDept = parentDept;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

}
