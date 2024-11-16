package com.miam.cloudApi.miamCloudApi.application.dto.clientsDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateLimitValues {

    @NotBlank(message = "Limit heart rate is mandatory")
    private int limitHeartRate;

    @NotBlank(message = "Limit temperature is mandatory")
    private int limitTemperature;

    @NotBlank(message = "Limit distance is mandatory")
    private int limitDistance;

}
