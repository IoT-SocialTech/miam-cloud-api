package com.miam.cloudApi.miamCloudApi.application.controller;

import com.miam.cloudApi.miamCloudApi.application.dto.clientsDto.*;
import com.miam.cloudApi.miamCloudApi.application.client.MetricsClient;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Feing Client Controller", description = "Feing Client Controller API")
@RestController
@RequestMapping("/api/v1/miam-cloud-api")
public class ClientController {

    private final MetricsClient metricsClient;

    public ClientController(MetricsClient metricsClient) {
        this.metricsClient = metricsClient;
    }

    @Operation(summary = "Get average heart rate")
    @GetMapping("/metrics/averageHeartRate/{id}")
    ResponseEntity<ApiResponse<AverageHeartRateResponseDto>> getAverageHeartRate(@PathVariable int id) {
        var res = metricsClient.getAverageHeartRate(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Get average temperature")
    @GetMapping("/metrics/averageTemperature/{id}")
    ResponseEntity<ApiResponse<AverageTemperatureResponseDto>> getAverageTemperature(@PathVariable int id) {
        var res = metricsClient.getAverageTemperature(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Get temperature")
    @GetMapping("/metrics/temperature/{id}")
    ResponseEntity<ApiResponse<TemperatureResponseDto>> getTemperature(@PathVariable int id) {
        var res = metricsClient.getTemperature(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Get heart rate")
    @GetMapping("/metrics/heartRate/{id}")
    ResponseEntity<ApiResponse<HeartRateResponseDto>> getHeartRate(@PathVariable int id) {
        var res = metricsClient.getHeartRate(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update device")
    @PostMapping("/device/{id}")
    ResponseEntity<ApiResponse<DeviceResponseDto>> updateDevice(@PathVariable String id, @RequestBody UpdateLimitValues updateLimitValues) {
        var res = metricsClient.updateDevice(id, updateLimitValues);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
