package com.miam.cloudApi.miamCloudApi.application.dto.request;

import com.miam.cloudApi.miamCloudApi.domain.enums.ReportType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportHistoryRequestDto {

    @NotBlank(message = "Patient id is mandatory")
    private int patientId;

    @NotBlank(message = "Caregiver id is mandatory")
    private int caregiverId;

    private Double temperature;

    private int heartRate;

}
