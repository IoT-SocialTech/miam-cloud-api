package com.miam.cloudApi.miamCloudApi.application.dto.response;

import com.miam.cloudApi.miamCloudApi.domain.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountResponseDto {

    private int id;

    private String email;

    private String password;

    private long phoneNumber;

    private boolean isActive;

    private LocalDateTime createdAt;

    private Role role;
}
