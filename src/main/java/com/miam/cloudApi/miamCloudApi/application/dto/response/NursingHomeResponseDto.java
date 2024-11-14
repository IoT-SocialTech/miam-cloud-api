package com.miam.cloudApi.miamCloudApi.application.dto.response;

import com.miam.cloudApi.miamCloudApi.domain.entities.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NursingHomeResponseDto {

    private int id;

    private String name;

    private String location;

    private Long ruc;

    private Account account;

}
