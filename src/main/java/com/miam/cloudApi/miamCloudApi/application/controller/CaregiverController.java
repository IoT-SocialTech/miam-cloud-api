package com.miam.cloudApi.miamCloudApi.application.controller;

import com.miam.cloudApi.miamCloudApi.application.dto.request.CaregiverRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.CaregiverResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.CaregiverService;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Caregiver", description = "Caregiver API")
@RestController
@RequestMapping("/api/v1/miam/cloudApi")
public class CaregiverController {

    private final CaregiverService caregiverService;

    public CaregiverController(CaregiverService caregiverService) {
        this.caregiverService = caregiverService;
    }

    @Operation(summary = "get a caregiver by id")
    @GetMapping("/caregivers/{id}")
    public ResponseEntity<ApiResponse<CaregiverResponseDto>> getCaregiverById(@PathVariable int id) {
        var res = caregiverService.getCaregiverById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "get a caregiver by account id")
    @GetMapping("/caregivers/account/{id}")
    public ResponseEntity<ApiResponse<CaregiverResponseDto>> getCaregiverByAccountId(@PathVariable int id) {
        var res = caregiverService.getCaregiverByAccountId(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "create a new caregiver")
    @PostMapping("/caregivers")
    public ResponseEntity<ApiResponse<CaregiverResponseDto>> createCaregiver(@RequestBody CaregiverRequestDto caregiverRequestDto) {
        var res = caregiverService.createCaregiver(caregiverRequestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing caregiver")
    @PutMapping("/caregivers/{id}")
    public ResponseEntity<ApiResponse<CaregiverResponseDto>> updateCaregiver(@PathVariable int id, @RequestBody CaregiverRequestDto caregiverRequestDto) {
        var res = caregiverService.updateCaregiver(id, caregiverRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a caregiver")
    @DeleteMapping("/caregivers/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCaregiver(@PathVariable int id) {
        var res = caregiverService.deleteCaregiver(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
