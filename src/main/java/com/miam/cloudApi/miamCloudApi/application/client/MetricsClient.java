package com.miam.cloudApi.miamCloudApi.application.client;

import com.miam.cloudApi.miamCloudApi.application.dto.clientsDto.*;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mian-edge-api", url = "https://miam-edge-api.onrender.com/api/v1/miam-edge-api")
public interface MetricsClient {

    @GetMapping("/metrics/averageHeartRate/{id}")
    ApiResponse<AverageHeartRateResponseDto> getAverageHeartRate(@PathVariable int id);

    @GetMapping("/metrics/averageTemperature/{id}")
    ApiResponse<AverageTemperatureResponseDto> getAverageTemperature(@PathVariable int id);

    @GetMapping("/metrics/heartRate/{id}")
    ApiResponse<HeartRateResponseDto> getHeartRate(@PathVariable int id);

    @GetMapping("/metrics/temperature/{id}")
    ApiResponse<TemperatureResponseDto> getTemperature(@PathVariable int id);

    @PostMapping("/device/{id}")
    ApiResponse<DeviceResponseDto> updateDevice(@PathVariable String id, @RequestBody UpdateLimitValues updateLimitValues);
}
