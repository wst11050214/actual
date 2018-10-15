package com.manageplat.dept.form;

import cn.gfire.base.mvc.form.BaseForm;
import com.manageplat.dept.domain.Department;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by CTY on 2018/8/9/009.
 */
public class DepartmentForm extends BaseForm<Department,Long> {

    @NotEmpty(message = "部门名称不能为空")
    private String deptName;

    private String deptNo;

    private Long parentDept;

    private Integer deptClass;

    public Integer getDeptClass() {
        return deptClass;
    }

    public void setDeptClass(Integer deptClass) {
        this.deptClass = deptClass;
    }

    public Long getParentDept() {
        return parentDept;
    }

    public void setParentDept(Long parentDept) {
        this.parentDept = parentDept;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String getName() {
        return null;
    }
}
