package com.miam.cloudApi.miamCloudApi.application.services;

import com.miam.cloudApi.miamCloudApi.application.dto.response.PatientCaregiverResponseDto;
import com.miam.cloudApi.miamCloudApi.domain.entities.PatientCaregiver;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;

public interface PatientCaregiverService {

    ApiResponse<PatientCaregiverResponseDto> getPatientCaregiverById(int patientId, int caregiverId);

}
