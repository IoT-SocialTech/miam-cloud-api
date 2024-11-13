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
public class NotificationRequestDto {

    @NotBlank(message = "title is mandatory")
    private String title;

    @NotBlank(message = "message is mandatory")
    private String message;

    @NotBlank(message = "caregiver is mandatory")
    private int caregiver;

}
