package com.miam.cloudApi.miamCloudApi.config;

import com.miam.cloudApi.miamCloudApi.application.client.interceptor.FeignClientInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor(FeignClientInterceptor feignClientInterceptor) {
        return feignClientInterceptor;
    }
}