package com.miam.cloudApi.miamCloudApi.application.services.impl;

import com.miam.cloudApi.miamCloudApi.application.dto.request.AccountRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.AccountResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.AccountService;
import com.miam.cloudApi.miamCloudApi.domain.entities.Account;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.AccountRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.RoleRepository;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import com.miam.cloudApi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public AccountServiceImpl(AccountRepository accountRepository, ModelMapper modelMapper, RoleRepository roleRepository) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public ApiResponse<AccountResponseDto> getAccountById(int id) {
        Optional<Account> accountOptional = accountRepository.findById(id);

        if (accountOptional.isEmpty()) {
            return new ApiResponse<>("Account not found", Estatus.ERROR, null);
        } else {
            Account account = accountOptional.get();
            AccountResponseDto response = modelMapper.map(account, AccountResponseDto.class);
            return new ApiResponse<>("Account found successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<AccountResponseDto> createAccount(AccountRequestDto accountRequestDTO) {

        var account = modelMapper.map(accountRequestDTO, Account.class);
        account.setRole(roleRepository.getRoleById(accountRequestDTO.getRole()));
        account.setCreatedAt(LocalDateTime.now());
        accountRepository.save(account);

        var response = modelMapper.map(account, AccountResponseDto.class);

        return new ApiResponse<>("Account created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<AccountResponseDto> updateAccount(int id, AccountRequestDto accountRequestDTO) {

        Optional<Account> accountOptional = accountRepository.findById(id);

        if (accountOptional.isEmpty()) {
            return new ApiResponse<>("Account not found", Estatus.ERROR, null);
        } else {
            Account account = accountOptional.get();
            modelMapper.map(accountRequestDTO, account);
            account.setRole(roleRepository.getRoleById(accountRequestDTO.getRole()));
            accountRepository.save(account);
            AccountResponseDto response = modelMapper.map(account, AccountResponseDto.class);
            return new ApiResponse<>("Account updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<Void> deleteAccount(int id) {

        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return new ApiResponse<>("Account deleted successfully", Estatus.SUCCESS, null);
        } else {
            return new ApiResponse<>("Account not found", Estatus.ERROR, null);
        }
    }
}
