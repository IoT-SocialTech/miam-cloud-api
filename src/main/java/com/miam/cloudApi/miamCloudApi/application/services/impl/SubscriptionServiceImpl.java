package com.miam.cloudApi.miamCloudApi.application.services.impl;

import com.miam.cloudApi.miamCloudApi.application.dto.request.SubscriptionRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.SubscriptionResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.SubscriptionService;
import com.miam.cloudApi.miamCloudApi.domain.entities.Subscription;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.AccountRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.PlanRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.SubscriptionRepository;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import com.miam.cloudApi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final AccountRepository accountRepository;
    private final PlanRepository planRepository;
    private final ModelMapper modelMapper;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, AccountRepository accountRepository, PlanRepository planRepository, ModelMapper modelMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.accountRepository = accountRepository;
        this.planRepository = planRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<SubscriptionResponseDto> getSubscriptionById(int id) {
        Optional<Subscription> optionalSubscription = subscriptionRepository.findById(id);

        if (optionalSubscription.isEmpty()){
            return new ApiResponse<>("Subscription not found", Estatus.ERROR, null);
        } else {
            Subscription subscription = optionalSubscription.get();
            SubscriptionResponseDto response = modelMapper.map(subscription, SubscriptionResponseDto.class);
            return new ApiResponse<>("Subscription found successfully", Estatus.SUCCESS, response);
        }

    }

    @Override
    public ApiResponse<SubscriptionResponseDto> createSubscription(SubscriptionRequestDto subscriptionRequestDto) {

        Subscription subscription = new Subscription();
        subscription.setPlan(planRepository.getPlanById(subscriptionRequestDto.getPlanId()));
        subscription.setType(subscriptionRequestDto.getType());
        subscription.setStartDate(LocalDateTime.now());
        subscription.setEndDate(LocalDateTime.now().plusMonths(1));
        subscription.setActive(subscription.isActive());

        subscriptionRepository.save(subscription);

        SubscriptionResponseDto subscriptionResponseDto = modelMapper.map(subscription, SubscriptionResponseDto.class);

        return new ApiResponse<>("Subscription created successfully", Estatus.SUCCESS, subscriptionResponseDto);
    }

    @Override
    public ApiResponse<SubscriptionResponseDto> updateSubscription(int id, SubscriptionRequestDto subscriptionRequestDto) {
        Optional<Subscription> optionalSubscription = subscriptionRepository.findById(id);

        if (optionalSubscription.isEmpty()){
            return new ApiResponse<>("Subscription not found", Estatus.ERROR, null);
        } else {
            Subscription subscription = optionalSubscription.get();
            modelMapper.map(subscriptionRequestDto, subscription);
            subscription.setPlan(planRepository.getPlanById(subscriptionRequestDto.getPlanId()));
            subscriptionRepository.save(subscription);

            SubscriptionResponseDto subscriptionResponseDto = modelMapper.map(subscription, SubscriptionResponseDto.class);

            return new ApiResponse<>("Subscription updated successfully", Estatus.SUCCESS, subscriptionResponseDto);
        }
    }

    @Override
    public ApiResponse<SubscriptionResponseDto> deleteSubscription(int id) {
        Optional<Subscription> optionalSubscription = subscriptionRepository.findById(id);

        if (optionalSubscription.isEmpty()){
            return new ApiResponse<>("Subscription not found", Estatus.ERROR, null);
        } else {
            subscriptionRepository.deleteById(id);
            return new ApiResponse<>("Subscription deleted successfully", Estatus.SUCCESS, null);
        }
    }

}
