package com.miam.cloudApi.miamCloudApi.application.dto.response;

import java.time.LocalDate;

import com.miam.cloudApi.miamCloudApi.domain.entities.Account;
import com.miam.cloudApi.miamCloudApi.domain.entities.Relative;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientResponseDto {

    private int id;

    private String name;

    private String lastName;

    private LocalDate birthDate;

    private String address;

    private Relative relative;

    private Account account;

}
