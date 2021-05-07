package com.adpro.tasc.user.db.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private String userName;
    private String fullName;
    private String password;
    private Role role;
}
