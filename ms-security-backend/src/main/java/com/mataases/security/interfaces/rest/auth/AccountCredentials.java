package com.mataases.security.interfaces.rest.auth;

import lombok.Data;

@Data
public class AccountCredentials {
    private String username;
    private String password;
}
