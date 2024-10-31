package com.miam.cloudApi.miamCloudApi.application.services;

import com.miam.cloudApi.miamCloudApi.application.dto.request.PatientRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.PatientResponseDto;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;

public interface PatientService {

    ApiResponse<PatientResponseDto> getPatientById(int id);

    ApiResponse<PatientResponseDto> createPatient(PatientRequestDto patientRequestDto);

    ApiResponse<PatientResponseDto> updatePatient(int id, PatientRequestDto patientRequestDto);

    ApiResponse<Void> deletePatient(int id);

}
