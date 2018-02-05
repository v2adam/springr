package com.springr.first.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.myNamedQ", query = "SELECT u FROM User u"),
        @NamedQuery(name = "User.findByNameA", query = "SELECT u FROM User u WHERE u.userName = :userName"),
})
public class User {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String userName;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;


    @Column(name = "create_at", insertable = false, nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    // így hozok létre egy kapcsolótáblát
    // ez foreign key lesz,
    // nem valós oszlop
    // cascade-al megmondom neki, hogy mikor mozogjanak együtt az objektumok
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, targetEntity = Role.class)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles = new HashSet<Role>();

    public User() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRoles(String roleName) {
        if (this.roles == null || this.roles.isEmpty()) {
            this.roles = new HashSet<>();
        }
        this.roles.add(new Role(roleName));
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", creationDate=" + creationDate +
                ", roles=" + roles +
                '}';
    }
}
