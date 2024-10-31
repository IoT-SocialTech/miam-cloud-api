package com.miam.cloudApi.miamCloudApi.application.controller;

import com.miam.cloudApi.miamCloudApi.application.dto.request.PatientRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.PatientResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.PatientService;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Patient", description = "Patient API")
@RestController
@RequestMapping("/api/v1/miam/cloudApi")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @Operation(summary = "Get patient by id")
    @GetMapping("/patients/{id}")
    public ResponseEntity<ApiResponse<PatientResponseDto>> getPatientById(@PathVariable int id) {
        var res = patientService.getPatientById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Create patient")
    @PostMapping("/patients")
    public ResponseEntity<ApiResponse<PatientResponseDto>> createPatient(@RequestBody PatientRequestDto patientRequestDto) {
        var res = patientService.createPatient(patientRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update patient")
    @PutMapping("/patients/{id}")
    public ResponseEntity<ApiResponse<PatientResponseDto>> updatePatient(@PathVariable int id, @RequestBody PatientRequestDto patientRequestDto) {
        var res = patientService.updatePatient(id, patientRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Delete patient")
    @DeleteMapping("/patients/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePatient(@PathVariable int id) {
        var res = patientService.deletePatient(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
