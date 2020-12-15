package com.userfront.domain.security;

import org.springframework.security.core.GrantedAuthority;


public class Authority implements GrantedAuthority{

    private final String authority;

    public Authority(String authority) {
        //constructor
        //REQUIRES: authority as String type
        //MODIFIES: this.authority
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        //EFFECTS: return authority data member value that has been initialized at constructor
        return authority;
    }
}
