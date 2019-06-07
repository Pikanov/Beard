package com.beard.entity;


import java.util.Objects;

public class Role {

    private Long roleId;
    private String role;

    public Role(Long roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    public Role() {
    }

    public Role(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role1 = (Role) o;
        return Objects.equals(roleId, role1.roleId) &&
                Objects.equals(role, role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, role);
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", role='" + role + '\'' +
                '}';
    }
}
