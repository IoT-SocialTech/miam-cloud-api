package com.miam.cloudApi.miamCloudApi.application.dto.response;

import com.miam.cloudApi.miamCloudApi.domain.entities.Caregiver;
import com.miam.cloudApi.miamCloudApi.domain.entities.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientCaregiverResponseDto {

    private PatientResponseDto patient;

    private CaregiverResponseDto caregiver;

}
