package com.miam.cloudApi.miamCloudApi.application.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicationScheduleRequest {

    @NotBlank(message = "Medication name is mandatory")
    private String medicationName;

    @NotBlank(message = "Medication dose is mandatory")
    private int dose;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @Schema(type = "string", pattern = "HH:mm:ss", example = "00:00:00")
    @NotBlank(message = "Medication hour is mandatory")
    private LocalTime hour;

    @NotBlank(message = "Medication taken is mandatory")
    private Boolean taken;

    @NotBlank(message = "Patient id is mandatory")
    private int patientId;

    @NotBlank(message = "Caregiver id is mandatory")
    private int caregiverId;
}
