package com.miam.cloudApi.miamCloudApi.application.services;

import com.miam.cloudApi.miamCloudApi.application.dto.response.PatientCaregiverResponseDto;
import com.miam.cloudApi.miamCloudApi.domain.entities.PatientCaregiver;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface PatientCaregiverService {

    ApiResponse<PatientCaregiverResponseDto> getPatientCaregiverById(int patientId, int caregiverId);

    ApiResponse<List<PatientCaregiverResponseDto>> getPatientCaregiverByCaregiverId(int id);

    ApiResponse<List<PatientCaregiverResponseDto>> getPatientCaregiverByPatientId(int id);

}
