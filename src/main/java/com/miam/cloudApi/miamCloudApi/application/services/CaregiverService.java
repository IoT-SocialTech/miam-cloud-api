package com.miam.cloudApi.miamCloudApi.application.services;

import com.miam.cloudApi.miamCloudApi.application.dto.request.CaregiverRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.CaregiverResponseDto;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;

public interface CaregiverService {

    ApiResponse<CaregiverResponseDto> getCaregiverById(int id);

    ApiResponse<CaregiverResponseDto> createCaregiver(CaregiverRequestDto caregiverRequestDto);

    ApiResponse<CaregiverResponseDto> updateCaregiver(int id, CaregiverRequestDto caregiverRequestDto);

    ApiResponse<Void> deleteCaregiver(int id);

}
