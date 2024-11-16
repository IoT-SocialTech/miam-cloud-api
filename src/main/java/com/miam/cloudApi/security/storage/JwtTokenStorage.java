package com.miam.cloudApi.security.storage;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenStorage {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
