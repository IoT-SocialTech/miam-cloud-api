package com.miam.cloudApi.miamCloudApi.application.dto.response;

import com.miam.cloudApi.miamCloudApi.domain.entities.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelativeResponseDto {

    private int id;

    private String name;

    private String lastName;

    private String relationship;

    private Account account;

}
