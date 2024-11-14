package com.miam.cloudApi.miamCloudApi.application.services;

import com.miam.cloudApi.miamCloudApi.application.dto.request.NursingHomeRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.NursingHomeResponseDto;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;

public interface NursingHomeService {

    ApiResponse<NursingHomeResponseDto> getNursingHomeById(int id);

    ApiResponse<NursingHomeResponseDto> createNursingHome(NursingHomeRequestDto nursingHomeRequestDto);

    ApiResponse<NursingHomeResponseDto> updateNursingHome(int id, NursingHomeRequestDto nursingHomeRequestDto);

    ApiResponse<Void> deleteNursingHome(int id);

}
