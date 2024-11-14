package com.miam.cloudApi.miamCloudApi.application.controller;

import com.miam.cloudApi.miamCloudApi.application.dto.response.CaregiversNursingHomesResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.CaregiversNursingHomesService;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Caregiver Nursing Home", description = "Caregiver Nursing Home API")
@RestController
@RequestMapping("/api/v1/miam/cloudApi")
public class CaregiversNursingHomeController {

    CaregiversNursingHomesService caregiversNursingHomesService;

    public CaregiversNursingHomeController(CaregiversNursingHomesService caregiversNursingHomesService) {
        this.caregiversNursingHomesService = caregiversNursingHomesService;
    }

    @Operation(summary = "get a caregiver nursing home by id")
    @GetMapping("/caregiversNursingHomes/{caregiverId}/{nursingHomeId}")
    public ResponseEntity<ApiResponse<CaregiversNursingHomesResponseDto>> getCaregiversNursingHomesById(@PathVariable int caregiverId, @PathVariable int nursingHomeId) {
        var res = caregiversNursingHomesService.getCaregiversNursingHomesById(caregiverId, nursingHomeId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "get a list of caregiver nursing homes by nursing home id")
    @GetMapping("/caregiversNursingHomes/nursingHome/{id}")
    public ResponseEntity<ApiResponse<List<CaregiversNursingHomesResponseDto>>> getCaregiversNursingHomesByNursingHomeId(@PathVariable int id) {
        var res = caregiversNursingHomesService.getCaregiversNursingHomesByNursingHomeId(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
