package com.miam.cloudApi.miamCloudApi.application.services.impl;

import com.miam.cloudApi.miamCloudApi.application.dto.request.PlanRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.PlanResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.PlanService;
import com.miam.cloudApi.miamCloudApi.domain.entities.Plan;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.PlanRepository;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final ModelMapper modelMapper;

    public PlanServiceImpl(PlanRepository planRepository, ModelMapper modelMapper) {
        this.planRepository = planRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<PlanResponseDto> getPlanById(int id) {
        Optional<Plan> optionalPlan = planRepository.findById(id);

        if (optionalPlan.isEmpty()){
            return new ApiResponse<>("Plan not found", null, null);
        } else {
            Plan plan = optionalPlan.get();
            PlanResponseDto response = modelMapper.map(plan, PlanResponseDto.class);
            return new ApiResponse<>("Plan found successfully", null, response);
        }

    }

    @Override
    public ApiResponse<List<PlanResponseDto>> getAllPlans() {
        List<Plan> plans = (List<Plan>) planRepository.findAll();

        List<PlanResponseDto> plansResponseDto = plans.stream()
                .map(plan -> modelMapper.map(plan, PlanResponseDto.class))
                .toList();

        return new ApiResponse<>("Plans found successfully", null, plansResponseDto);
    }

    @Override
    public ApiResponse<PlanResponseDto> createPlan(PlanRequestDto planRequestDto) {
        var plan = modelMapper.map(planRequestDto, Plan.class);
        planRepository.save(plan);

        return new ApiResponse<>("Plan created successfully", null, modelMapper.map(plan, PlanResponseDto.class));
    }

    @Override
    public ApiResponse<PlanResponseDto> updatePlan(int id, PlanRequestDto planRequestDto) {
        Optional<Plan> optionalPlan = planRepository.findById(id);

        if (optionalPlan.isEmpty()){
            return new ApiResponse<>("Plan not found", null, null);
        } else {
            Plan plan = optionalPlan.get();
            modelMapper.map(planRequestDto, plan);
            planRepository.save(plan);

            PlanResponseDto planResponseDto = modelMapper.map(plan, PlanResponseDto.class);
            return new ApiResponse<>("Plan updated successfully", null, modelMapper.map(plan, PlanResponseDto.class));
        }
    }

    @Override
    public ApiResponse<PlanResponseDto> deletePlan(int id) {
        Optional<Plan> optionalPlan = planRepository.findById(id);

        if (optionalPlan.isEmpty()){
            return new ApiResponse<>("Plan not found", null, null);
        } else {
            Plan plan = optionalPlan.get();
            planRepository.delete(plan);

            return new ApiResponse<>("Plan deleted successfully", null, modelMapper.map(plan, PlanResponseDto.class));
        }
    }

}
