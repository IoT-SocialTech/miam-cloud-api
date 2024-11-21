package com.miam.cloudApi.miamCloudApi.application.controller;

import com.miam.cloudApi.miamCloudApi.application.dto.request.MedicationScheduleRequest;
import com.miam.cloudApi.miamCloudApi.application.dto.request.UpdateMedicationScheduleTaken;
import com.miam.cloudApi.miamCloudApi.application.dto.response.MedicationScheduleResponse;
import com.miam.cloudApi.miamCloudApi.application.services.MedicationScheduleService;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Medication Schedule", description = "Medication Schedule API")
@RestController
@RequestMapping("/api/v1/miam/cloudApi")
public class MedicationScheduleController {

    MedicationScheduleService medicationScheduleService;

    public MedicationScheduleController(MedicationScheduleService medicationScheduleService) {
        this.medicationScheduleService = medicationScheduleService;
    }

    @Operation(summary = "get a medication schedule by id")
    @GetMapping("/medicationSchedules/patient/{patientId}")
    public ResponseEntity<ApiResponse<List<MedicationScheduleResponse>>> getMedicationScheduleByPatientId(@PathVariable int patientId) {
        var res = medicationScheduleService.getMedicationScheduleByPatientId(patientId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "get a medication schedule by id")
    @GetMapping("/medicationSchedules/{id}")
    public ResponseEntity<ApiResponse<MedicationScheduleResponse>> getMedicationScheduleById(@PathVariable int id) {
        var res = medicationScheduleService.getMedicationScheduleById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "create a new medication schedule")
    @PostMapping("/medicationSchedules")
    public ResponseEntity<ApiResponse<MedicationScheduleResponse>> createMedicationSchedule(@RequestBody MedicationScheduleRequest medicationScheduleRequest) {
        var res = medicationScheduleService.createMedicationSchedule(medicationScheduleRequest);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing medication schedule")
    @PutMapping("/medicationSchedules/{id}")
    public ResponseEntity<ApiResponse<MedicationScheduleResponse>> updateMedicationSchedule(@PathVariable int id, @RequestBody UpdateMedicationScheduleTaken updateMedicationScheduleTaken) {
        var res = medicationScheduleService.updateMedicationSchedule(id, updateMedicationScheduleTaken);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a medication schedule")
    @DeleteMapping("/medicationSchedules/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMedicationSchedule(@PathVariable int id) {
        var res = medicationScheduleService.deleteMedicationSchedule(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "get a medication schedule by caregiver id")
    @GetMapping("/medicationSchedules/caregiver/{caregiverId}")
    public ResponseEntity<ApiResponse<List<MedicationScheduleResponse>>> getMedicationScheduleByCaregiverId(@PathVariable int caregiverId) {
        var res = medicationScheduleService.getMedicationScheduleByCaregiverId(caregiverId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
