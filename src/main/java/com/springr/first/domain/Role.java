package com.springr.first.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    private String role;


    // nem lesz végtelen körbehivatkozás
    @JsonIgnore
    // a users a users tulajdona, jelölve van, hogy a User.role-ban végződik
    // ez nem egy valós oszlop
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER, targetEntity = User.class)
    private Set<User> users = new HashSet<User>();


    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", users=" + users +
                '}';
    }

}
