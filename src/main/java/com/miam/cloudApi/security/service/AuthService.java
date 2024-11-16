package com.miam.cloudApi.security.service;

import com.miam.cloudApi.security.model.request.LoginRequestDto;
import com.miam.cloudApi.security.model.request.RegisterRequestDto;
import com.miam.cloudApi.security.model.response.RegisteredUserResponseDto;
import com.miam.cloudApi.security.model.response.TokenResponseDto;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;

public interface AuthService {

    ApiResponse<RegisteredUserResponseDto> registerUser(RegisterRequestDto request);

    ApiResponse<TokenResponseDto> login(LoginRequestDto request);

}
