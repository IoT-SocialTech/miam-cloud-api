package com.miam.cloudApi.miamCloudApi.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientRequestDto {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotBlank(message = "Age is mandatory")
    private int age;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Birthdate is mandatory")
    private LocalDate birthdate;

    @NotBlank(message = "Account is mandatory")
    private int account;

    @NotBlank(message = "Relative is mandatory")
    private int relative;

    private List<Integer> caregiverIds;

}
