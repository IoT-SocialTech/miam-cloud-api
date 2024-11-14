package com.miam.cloudApi.miamCloudApi.application.controller;

import com.miam.cloudApi.miamCloudApi.application.dto.request.NursingHomeRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.NursingHomeResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.NursingHomeService;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Nursing Home", description = "Nursing Home API")
@RestController
@RequestMapping("/api/v1/miam/cloudApi")
public class NursingHomeController {

    NursingHomeService nursingHomeService;

    public NursingHomeController(NursingHomeService nursingHomeService) {
        this.nursingHomeService = nursingHomeService;
    }

    @Operation(summary = "get a nursing home by id")
    @GetMapping("/nursingHomes/{id}")
    public ResponseEntity<ApiResponse<NursingHomeResponseDto>> getNursingHomeById(@PathVariable int id) {
        var res = nursingHomeService.getNursingHomeById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "create a nursing home")
    @PostMapping("/nursingHomes")
    public ResponseEntity<ApiResponse<NursingHomeResponseDto>> createNursingHome(@RequestBody NursingHomeRequestDto nursingHomeRequestDto) {
        var res = nursingHomeService.createNursingHome(nursingHomeRequestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update a nursing home")
    @PutMapping("/nursingHomes/{id}")
    public ResponseEntity<ApiResponse<NursingHomeResponseDto>> updateNursingHome(@PathVariable int id, @RequestBody NursingHomeRequestDto nursingHomeRequestDto) {
        var res = nursingHomeService.updateNursingHome(id, nursingHomeRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a nursing home")
    @DeleteMapping("/nursingHomes/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteNursingHome(@PathVariable int id) {
        var res = nursingHomeService.deleteNursingHome(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
