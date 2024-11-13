package com.miam.cloudApi.miamCloudApi.application.services;

import com.miam.cloudApi.miamCloudApi.application.dto.request.NotificationRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.request.UpdateNotificationStatus;
import com.miam.cloudApi.miamCloudApi.application.dto.response.NotificationResponseDto;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface NotificationService {

    ApiResponse<NotificationResponseDto> getNotification(int id);

    ApiResponse<List<NotificationResponseDto>> getNotificationsByCaregiver(int caregiverId);

    ApiResponse<NotificationResponseDto> createNotification(NotificationRequestDto notificationRequestDto);

    ApiResponse<NotificationResponseDto> updateNotification(int id, UpdateNotificationStatus updateNotificationStatus);

    ApiResponse<Void> deleteNotification(int id);

}
