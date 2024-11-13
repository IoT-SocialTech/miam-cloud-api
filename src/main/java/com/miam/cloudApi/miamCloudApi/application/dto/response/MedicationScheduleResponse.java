package com.miam.cloudApi.miamCloudApi.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicationScheduleResponse {

    private int id;

    private String medicationName;

    private int dose;

    private LocalTime hour;

    private Boolean taken;

    private int patientId;

    private int caregiverId;

}
