package com.miam.cloudApi.miamCloudApi.application.services;

import com.miam.cloudApi.miamCloudApi.application.dto.request.AccountRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.AccountResponseDto;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;

public interface AccountService {

    ApiResponse<AccountResponseDto> getAccountById(int id);

    ApiResponse<AccountResponseDto> createAccount(AccountRequestDto accountRequestDTO);

    ApiResponse<AccountResponseDto> updateAccount(int id, AccountRequestDto accountRequestDTO);

    ApiResponse<Void> deleteAccount(int id);

}
