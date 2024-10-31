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
public class PatientCaregiverRequest {

    @NotBlank(message = "Patient is mandatory")
    private int patient;

    @NotBlank(message = "Caregiver is mandatory")
    private int caregiver;

}
