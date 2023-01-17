package com.accenture.codingtest.springbootcodingtest.model;


import com.accenture.codingtest.springbootcodingtest.enums.ROLES_ENUMS;

import static com.accenture.codingtest.springbootcodingtest.enums.ROLES_ENUMS.DEFAULT_USER;

public class UserDto {

    private String id;


    private String username;


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

    public ROLES_ENUMS role=DEFAULT_USER;

    public ROLES_ENUMS getRole() {
        return role;
    }

    public void setRole(ROLES_ENUMS role) {
        this.role = role;
    }
}
