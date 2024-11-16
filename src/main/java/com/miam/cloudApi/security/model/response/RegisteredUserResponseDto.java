package com.miam.cloudApi.security.model.response;

import com.miam.cloudApi.miamCloudApi.domain.entities.Role;
import com.miam.cloudApi.miamCloudApi.domain.entities.Subscription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredUserResponseDto {

    private int id;

    private String email;

    private String password;

    private long phoneNumber;

    private boolean isActive;

    private LocalDateTime createdAt;

    private Subscription subscription;

    private Role role;
}
