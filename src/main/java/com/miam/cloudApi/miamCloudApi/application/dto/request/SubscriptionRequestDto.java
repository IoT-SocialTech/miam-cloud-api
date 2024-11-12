package com.miam.cloudApi.miamCloudApi.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionRequestDto {

    @NotBlank(message = "Type is mandatory")
    private String type;

    @NotBlank(message = "Plan id is mandatory")
    private int planId;

    @NotBlank(message = "Is active is mandatory")
    private Boolean isActive;

}
