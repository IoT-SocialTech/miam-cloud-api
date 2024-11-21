package com.miam.cloudApi.miamCloudApi.application.client.interceptor;

import com.miam.cloudApi.security.jwt.provider.JwtTokenProvider;
import com.miam.cloudApi.security.storage.JwtTokenStorage;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private final JwtTokenStorage jwtTokenStorage;

    public FeignClientInterceptor(JwtTokenStorage jwtTokenStorage) {
        this.jwtTokenStorage = jwtTokenStorage;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = jwtTokenStorage.getToken();
        if (token != null && !token.isEmpty()) {
            requestTemplate.header("Authorization", "Bearer " + token);
        }

        System.out.println("Authorization Header: Bearer " + token);
    }
}
