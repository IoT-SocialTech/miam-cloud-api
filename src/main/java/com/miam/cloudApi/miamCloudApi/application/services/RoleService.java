package com.miam.cloudApi.miamCloudApi.application.services;

import com.miam.cloudApi.miamCloudApi.application.dto.request.RoleRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.RoleResponseDto;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;

public interface RoleService {

    ApiResponse<RoleResponseDto> getRoleById(int id);

    ApiResponse<RoleResponseDto> createRole(RoleRequestDto roleRequestDto);

    ApiResponse<RoleResponseDto> updateRole(int id, RoleRequestDto roleRequestDto);

    ApiResponse<Void> deleteRole(int id);

}
