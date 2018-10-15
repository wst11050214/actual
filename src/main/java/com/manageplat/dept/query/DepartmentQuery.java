package com.manageplat.dept.query;

import cn.gfire.base.jpa.dao.query.BaseWordQuery;
import cn.gfire.base.jpa.dao.query.MatchType;
import cn.gfire.base.jpa.dao.query.QueryWord;
import com.manageplat.dept.domain.Department;

/**
 * Created by CTY on 2018/8/9/009.
 */
public class DepartmentQuery extends BaseWordQuery<Department> {

    @QueryWord(matchType =MatchType.like)
    private String deptName;

    @QueryWord(matchType = MatchType.eq,value = "parentDept.id")
    private Long parentDept;


    @QueryWord(matchType = MatchType.eq)
    private Integer deptClass;

    public Integer getDeptClass() {
        return deptClass;
    }

    public void setDeptClass(Integer deptClass) {
        this.deptClass = deptClass;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getParentDept() {
        return parentDept;
    }

    public void setParentDept(Long parentDept) {
        this.parentDept = parentDept;
    }
}
