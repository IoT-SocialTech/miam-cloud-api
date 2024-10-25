package com.miam.cloudApi.miamCloudApi.application.controller;

import com.miam.cloudApi.miamCloudApi.application.dto.clientsDto.AverageHeartRateResponseDto;
import com.miam.cloudApi.miamCloudApi.application.dto.clientsDto.AverageTemperatureResponseDto;
import com.miam.cloudApi.miamCloudApi.application.dto.clientsDto.HeartRateResponseDto;
import com.miam.cloudApi.miamCloudApi.application.dto.clientsDto.TemperatureResponseDto;
import com.miam.cloudApi.miamCloudApi.client.MetricsClient;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Feing Client Controller", description = "Feing Client Controller API")
@RestController
@RequestMapping("/api/v1/miam-cloud-api")
public class ClientController {

    private final MetricsClient metricsClient;

    public ClientController(MetricsClient metricsClient) {
        this.metricsClient = metricsClient;
    }

    @Operation(summary = "Get average heart rate")
    @GetMapping("/metrics/averageHeartRate")
    ResponseEntity<ApiResponse<AverageHeartRateResponseDto>> getAverageHeartRate() {
        var res = metricsClient.getAverageHeartRate();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Get average temperature")
    @GetMapping("/metrics/averageTemperature")
    ResponseEntity<ApiResponse<AverageTemperatureResponseDto>> getAverageTemperature() {
        var res = metricsClient.getAverageTemperature();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Get temperature")
    @GetMapping("/metrics/temperature")
    ResponseEntity<ApiResponse<TemperatureResponseDto>> getTemperature() {
        var res = metricsClient.getTemperature();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Get heart rate")
    @GetMapping("/metrics/heartRate")
    ResponseEntity<ApiResponse<HeartRateResponseDto>> getHeartRate() {
        var res = metricsClient.getHeartRate();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
