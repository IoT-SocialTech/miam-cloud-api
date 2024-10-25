package com.miam.cloudApi.miamCloudApi.client;

import com.miam.cloudApi.miamCloudApi.application.dto.clientsDto.AverageHeartRateResponseDto;
import com.miam.cloudApi.miamCloudApi.application.dto.clientsDto.AverageTemperatureResponseDto;
import com.miam.cloudApi.miamCloudApi.application.dto.clientsDto.HeartRateResponseDto;
import com.miam.cloudApi.miamCloudApi.application.dto.clientsDto.TemperatureResponseDto;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "mian-edge-api", url = "http://localhost:8081/api/v1/miam-edge-api")
public interface MetricsClient {

    @GetMapping("/metrics/averageHeartRate")
    ApiResponse<AverageHeartRateResponseDto> getAverageHeartRate();

    @GetMapping("/metrics/averageTemperature")
    ApiResponse<AverageTemperatureResponseDto> getAverageTemperature();

    @GetMapping("/metrics/heartRate")
    ApiResponse<HeartRateResponseDto> getHeartRate();

    @GetMapping("/metrics/temperature")
    ApiResponse<TemperatureResponseDto> getTemperature();
}
