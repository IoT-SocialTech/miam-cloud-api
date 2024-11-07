package com.miam.cloudApi.miamCloudApi.application.controller;

import com.miam.cloudApi.miamCloudApi.application.dto.request.PlanRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.PlanResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.PlanService;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Plan Controller", description = "Plan Controller API")
@RestController
@RequestMapping("/api/v1/miam-cloud-api")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @Operation(summary = "Get plan by id")
    @GetMapping("/plans/{id}")
    public ResponseEntity<ApiResponse<PlanResponseDto>> getPlanById(@PathVariable int id) {
        var res = planService.getPlanById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Get all plans")
    @GetMapping("/plans")
    public ResponseEntity<ApiResponse<List<PlanResponseDto>>> getAllPlans() {
        var res = planService.getAllPlans();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Create plan")
    @PostMapping("/plans")
    public ResponseEntity<ApiResponse<PlanResponseDto>> createPlan(@RequestBody PlanRequestDto planRequestDto) {
        var res = planService.createPlan(planRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update plan")
    @PutMapping("/plans/{id}")
    public ResponseEntity<ApiResponse<PlanResponseDto>> updatePlan(@PathVariable int id, @RequestBody PlanRequestDto planRequestDto) {
        var res = planService.updatePlan(id, planRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Delete plan")
    @DeleteMapping("/plans/{id}")
    public ResponseEntity<ApiResponse<PlanResponseDto>> deletePlan(@PathVariable int id) {
        var res = planService.deletePlan(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
