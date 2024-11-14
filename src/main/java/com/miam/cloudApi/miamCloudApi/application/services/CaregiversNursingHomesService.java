package com.miam.cloudApi.miamCloudApi.application.services;

import com.miam.cloudApi.miamCloudApi.application.dto.response.CaregiversNursingHomesResponseDto;
import com.miam.cloudApi.miamCloudApi.domain.entities.CaregiversNursingHomes;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface CaregiversNursingHomesService {

    ApiResponse<CaregiversNursingHomesResponseDto> getCaregiversNursingHomesById(int caregiverId, int nursingHomeId);

    ApiResponse<List<CaregiversNursingHomesResponseDto>> getCaregiversNursingHomesByNursingHomeId(int id);

}
