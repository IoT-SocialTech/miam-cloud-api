package com.miam.cloudApi.miamCloudApi.application.controller;

import com.miam.cloudApi.miamCloudApi.application.dto.request.ReportHistoryRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.request.UpdateHistoryRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.request.UpdateReportHistoryStatus;
import com.miam.cloudApi.miamCloudApi.application.dto.response.ReportHistoryResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.ReportHistoryService;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Report History", description = "Report History API")
@RestController
@RequestMapping("/api/v1/miam/cloudApi")
public class ReportHistoryController {

    ReportHistoryService reportHistoryService;

    public ReportHistoryController(ReportHistoryService reportHistoryService) {
        this.reportHistoryService = reportHistoryService;
    }

    @Operation(summary = "get a report history by id")
    @GetMapping("/reportHistories/{id}")
    public ResponseEntity<ApiResponse<ReportHistoryResponseDto>> getReportHistoryById(@PathVariable int id) {
        var res = reportHistoryService.getReportHistoryById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "get a report history by patient id")
    @GetMapping("/reportHistories/patient/{patientId}")
    public ResponseEntity<ApiResponse<List<ReportHistoryResponseDto>>> getReportHistoryByPatientId(@PathVariable int patientId) {
        var res = reportHistoryService.getReportHistoryByPatientId(patientId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "get a report history by caregiver id")
    @GetMapping("/reportHistories/caregiver/{caregiverId}")
    public ResponseEntity<ApiResponse<List<ReportHistoryResponseDto>>> getReportHistoryByCaregiverId(@PathVariable int caregiverId) {
        var res = reportHistoryService.getReportHistoryByCaregiverId(caregiverId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "create a new report history")
    @PostMapping("/reportHistories")
    public ResponseEntity<ApiResponse<ReportHistoryResponseDto>> createReportHistory(@RequestBody ReportHistoryRequestDto reportHistoryRequestDto) {
        var res = reportHistoryService.createReportHistory(reportHistoryRequestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing report history")
    @PutMapping("/reportHistories/{id}")
    public ResponseEntity<ApiResponse<ReportHistoryResponseDto>> updateReportHistory(@PathVariable int id, @RequestBody UpdateHistoryRequestDto updateHistoryRequestDto) {
        var res = reportHistoryService.updateReportHistory(id, updateHistoryRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a report history")
    @DeleteMapping("/reportHistories/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteReportHistory(@PathVariable int id) {
        var res = reportHistoryService.deleteReportHistory(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
