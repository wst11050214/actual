package com.manageplat.admin.domain;

import cn.gfire.base.jpa.domain.BaseDomain;
import cn.gfire.base.jpa.domain.DomainName;
import org.hibernate.cfg.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wust
 * @create 2018-04-17 15:08
 * DESC
 **/
@Entity
@DomainName("权限")
public class Permission extends BaseDomain<Long> {

    @Column(
            name = "MOD_CODE"
    )
    private String modCode;
    @Column(
            name = "PREM_STATE"
    )
    private String permState;
    @Comment("模块名称")
    private String modName;
    @Comment("所属父模块")
    private String parentModeName;
    @Comment("关联的角色")
    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "permissions"
    )
    private List<Role> roles = new ArrayList();


    public String getModCode() {
        return modCode;
    }

    public void setModCode(String modCode) {
        this.modCode = modCode;
    }

    public String getPermState() {
        return permState;
    }

    public void setPermState(String permState) {
        this.permState = permState;
    }

    public String getModName() {
        return modName;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }

    public String getParentModeName() {
        return parentModeName;
    }

    public void setParentModeName(String parentModeName) {
        this.parentModeName = parentModeName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * 复写equils 以id 相同进行比较
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        Permission permission = (Permission) obj;
        return  this.id==permission.getId();
    }
}
