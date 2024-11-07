package com.miam.cloudApi.miamCloudApi.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanResponseDto {

    private int id;

    private String name;

    private String description;

    private double price;

}
