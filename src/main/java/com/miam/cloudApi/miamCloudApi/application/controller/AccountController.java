package com.miam.cloudApi.miamCloudApi.application.controller;

import com.miam.cloudApi.miamCloudApi.application.dto.request.AccountRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.AccountResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.AccountService;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Account", description = "Account API")
@RestController
@RequestMapping("/api/v1/miam/cloudApi")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "get a account by id")
    @GetMapping("/accounts/{id}")
    public ResponseEntity<ApiResponse<AccountResponseDto>> getAccountById(@PathVariable int id) {
        var res = accountService.getAccountById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "create a new account")
    @PostMapping("/accounts")
    public ResponseEntity<ApiResponse<AccountResponseDto>> createAccount(@RequestBody AccountRequestDto accountRequestDto) {
        var res = accountService.createAccount(accountRequestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing account")
    @PutMapping("/accounts/{id}")
    public ResponseEntity<ApiResponse<AccountResponseDto>> updateAccount(@PathVariable int id, @RequestBody AccountRequestDto accountRequestDto) {
        var res = accountService.updateAccount(id, accountRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a account")
    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAccount(@PathVariable int id) {
        var res = accountService.deleteAccount(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
