package com.mabao.admin.pojo;

import javax.persistence.*;

@Entity
@Table(name = "t_role", schema = "mabao", catalog = "")
public class Role {
    private Long id;                    //角色编号
    private String name;                //角色代号
    private String roleName;            //角色名

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
