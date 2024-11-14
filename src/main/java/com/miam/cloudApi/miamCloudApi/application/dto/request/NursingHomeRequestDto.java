package com.miam.cloudApi.miamCloudApi.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NursingHomeRequestDto {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Ubicacion is mandatory")
    private String location;

    @NotBlank(message = "Ruc is mandatory")
    private Long ruc;

    @NotBlank(message = "Account is mandatory")
    private int account;

    List<Integer> caregiverIds;

}
