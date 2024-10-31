package com.miam.cloudApi.miamCloudApi.application.controller;

import com.miam.cloudApi.miamCloudApi.application.dto.response.PatientCaregiverResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.PatientCaregiverService;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "PatientCaregiver", description = "PatientCaregiver API")
@RestController
@RequestMapping("/api/v1/miam/cloudApi")
public class PatientCaregiverController {

    private final PatientCaregiverService patientCaregiverService;

    public PatientCaregiverController(PatientCaregiverService patientCaregiverService) {
        this.patientCaregiverService = patientCaregiverService;
    }

    @Operation(summary = "get a patient caregiver by id")
    @GetMapping("/patientCaregivers/{patientId}/{caregiverId}")
    public ResponseEntity<ApiResponse<PatientCaregiverResponseDto>> getPatientCaregiverById(
            @PathVariable int patientId,
            @PathVariable int caregiverId) {

        var res = patientCaregiverService.getPatientCaregiverById(patientId, caregiverId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "create a new patient caregiver")
    @PutMapping("/patientCaregivers/{patientId}/{caregiverId}")
    public ResponseEntity<ApiResponse<PatientCaregiverResponseDto>> createPatientCaregiver(
            @PathVariable int patientId,
            @PathVariable int caregiverId) {

        var res = patientCaregiverService.createPatientCaregiver(patientId, caregiverId);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

}
