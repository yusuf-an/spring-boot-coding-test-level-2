package com.accenture.codingtest.springbootcodingtest.entity;

import com.accenture.codingtest.springbootcodingtest.enums.ROLES_ENUMS;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

import static com.accenture.codingtest.springbootcodingtest.enums.ROLES_ENUMS.DEFAULT_USER;

@Entity
public class User {

    @Id
    @Column(name = "id", length = 40)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @Column(unique=true,nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Enumerated(EnumType.STRING)
    public ROLES_ENUMS role=DEFAULT_USER;

    public ROLES_ENUMS getRole() {
        return role;
    }

    public void setRole(ROLES_ENUMS role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
