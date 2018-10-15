package com.manageplat.admin.domain;

import cn.gfire.base.jpa.domain.BaseDomain;
import groovy.lang.Lazy;
import org.hibernate.cfg.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wust
 * @create 2018-04-17 15:07
 * DESC
 **/
@Entity
public class Role extends BaseDomain<Long> {

    private String roleName;


    @Comment("对应用户")
    @OneToMany(
            mappedBy = "role"
    )
    private List<AdminUser> adminUsers = new ArrayList();



    @Comment("对应的权限")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = {@JoinColumn(
                    referencedColumnName = "id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    referencedColumnName = "id"
            )}
    )
    private List<Permission> permissions = new ArrayList();

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<AdminUser> getAdminUsers() {
        return adminUsers;
    }

    public void setAdminUsers(List<AdminUser> adminUsers) {
        this.adminUsers = adminUsers;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
