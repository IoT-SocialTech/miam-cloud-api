package com.miam.cloudApi.miamCloudApi.application.dto.request;

import com.miam.cloudApi.miamCloudApi.domain.enums.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateNotificationStatus {

    NotificationStatus status;

}
