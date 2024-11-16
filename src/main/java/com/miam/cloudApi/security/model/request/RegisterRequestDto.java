package com.miam.cloudApi.security.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestDto {

    @NotEmpty(message = "The email is required")
    @Email(message = "email format is invalid")
    private String email;

    @NotEmpty(message = "The password is required")
    @Size(min = 3, message = "the password must be at least 3 characters long")
    private String password;

    @NotEmpty(message = "Phone is required")
    private long phoneNumber;

    @NotEmpty(message = "Is Active is required")
    private boolean isActive;

    @NotEmpty(message = "Subscription is required")
    private int subscription;

    @NotEmpty(message = "Role is required")
    private int role;
}
