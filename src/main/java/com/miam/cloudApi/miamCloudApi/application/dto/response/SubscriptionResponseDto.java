package com.miam.cloudApi.miamCloudApi.application.dto.response;

import com.miam.cloudApi.miamCloudApi.domain.entities.Account;
import com.miam.cloudApi.miamCloudApi.domain.entities.Plan;
import com.miam.cloudApi.miamCloudApi.domain.entities.Subscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionResponseDto {

    private int id;

    private String startDate;

    private String endDate;

    private Boolean isActive;

    private String type;

    private Account account;

    private Subscription subscription;

    private Plan plan;

}
