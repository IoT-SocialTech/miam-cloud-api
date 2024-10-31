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
public class RelativeRequestDto {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotBlank(message = "Relationship is mandatory")
    private String relationship;

    @NotBlank(message = "Account is mandatory")
    private int account;

}
