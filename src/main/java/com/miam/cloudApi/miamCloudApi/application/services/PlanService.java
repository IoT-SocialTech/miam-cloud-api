package com.miam.cloudApi.miamCloudApi.application.services;

import com.miam.cloudApi.miamCloudApi.application.dto.request.PlanRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.PlanResponseDto;
import com.miam.cloudApi.miamCloudApi.domain.entities.Plan;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface PlanService {

    ApiResponse<PlanResponseDto> getPlanById(int id);

    ApiResponse<List<PlanResponseDto>> getAllPlans();

    ApiResponse<PlanResponseDto> createPlan(PlanRequestDto planRequestDto);

    ApiResponse<PlanResponseDto> updatePlan(int id, PlanRequestDto planRequestDto);

    ApiResponse<PlanResponseDto> deletePlan(int id);

}
