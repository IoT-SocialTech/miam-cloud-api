package com.miam.cloudApi.miamCloudApi.application.services;

import com.miam.cloudApi.miamCloudApi.application.dto.request.SubscriptionRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.SubscriptionResponseDto;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;

public interface SubscriptionService {

    ApiResponse<SubscriptionResponseDto> getSubscriptionById(int id);

    ApiResponse<SubscriptionResponseDto> createSubscription(SubscriptionRequestDto subscriptionRequestDto);

    ApiResponse<SubscriptionResponseDto> updateSubscription(int id, SubscriptionRequestDto subscriptionRequestDto);

    ApiResponse<SubscriptionResponseDto> deleteSubscription(int id);

}
