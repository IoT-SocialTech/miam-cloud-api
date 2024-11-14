package com.miam.cloudApi.miamCloudApi.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CaregiversNursingHomesRequestDto {

    @NotBlank(message = "Caregiver id is mandatory")
    private int caregiverId;

    @NotBlank(message = "Nursing home id is mandatory")
    private int nursingHomeId;

}
