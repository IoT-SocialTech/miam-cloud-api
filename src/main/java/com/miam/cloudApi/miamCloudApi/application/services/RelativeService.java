package com.miam.cloudApi.miamCloudApi.application.services;

import com.miam.cloudApi.miamCloudApi.application.dto.request.RelativeRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.RelativeResponseDto;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;

public interface RelativeService {

    ApiResponse<RelativeResponseDto> getRelativeById(int id);

    ApiResponse<RelativeResponseDto> createRelative(RelativeRequestDto relativeRequestDto);

    ApiResponse<RelativeResponseDto> updateRelative(int id, RelativeRequestDto relativeRequestDto);

    ApiResponse<Void> deleteRelative(int id);

}
