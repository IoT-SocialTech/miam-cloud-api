package com.miam.cloudApi.miamCloudApi.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRequestDto {

    @NotBlank(message = "email is mandatory")
    private String email;

    @NotBlank(message = "password is mandatory")
    private String password;

    @NotBlank(message = "phoneNumber is mandatory")
    private long phoneNumber;

    @NotBlank(message = "isActive is mandatory")
    private boolean isActive;

    @NotBlank(message = "Subscription is mandatory")
    private int subscription;

    @NotBlank(message = "role is mandatory")
    private int role;
}
