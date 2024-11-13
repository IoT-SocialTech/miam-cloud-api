package com.miam.cloudApi.miamCloudApi.application.services.impl;

import com.miam.cloudApi.miamCloudApi.application.dto.request.NotificationRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.request.UpdateNotificationStatus;
import com.miam.cloudApi.miamCloudApi.application.dto.response.NotificationResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.NotificationService;
import com.miam.cloudApi.miamCloudApi.domain.entities.Notification;
import com.miam.cloudApi.miamCloudApi.domain.enums.NotificationStatus;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.CaregiverRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.NotificationRepository;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import com.miam.cloudApi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    NotificationRepository notificationRepository;
    CaregiverRepository caregiverRepository;
    ModelMapper modelMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository, CaregiverRepository caregiverRepository, ModelMapper modelMapper){
        this.notificationRepository = notificationRepository;
        this.caregiverRepository = caregiverRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<NotificationResponseDto> getNotification(int id) {
        Optional<Notification> optionalNotification = notificationRepository.findById(id);
        if (optionalNotification.isEmpty()){
            return new ApiResponse<>("Notification not found", Estatus.ERROR, null);
        } else {
            Notification notification = optionalNotification.get();
            NotificationResponseDto notificationResponseDto = NotificationResponseDto.builder()
                    .id(notification.getId())
                    .title(notification.getTitle())
                    .caregiver(notification.getCaregiver().getId())
                    .date(notification.getDate())
                    .status(notification.getStatus())
                    .message(notification.getMessage())
                    .build();
            return new ApiResponse<>("Notification found successfully", Estatus.SUCCESS, notificationResponseDto);
        }
    }

    @Override
    public ApiResponse<List<NotificationResponseDto>> getNotificationsByCaregiver(int caregiverId) {
        List<Notification> notifications = notificationRepository.getNotificationByCaregiverId(caregiverId);
        if (notifications.isEmpty()){
            return new ApiResponse<>("Notifications not found", Estatus.ERROR, null);
        } else {
            List<NotificationResponseDto> notificationResponseDtoList = notifications.stream()
                    .map(notification -> NotificationResponseDto.builder()
                            .id(notification.getId())
                            .title(notification.getTitle())
                            .caregiver(notification.getCaregiver().getId())
                            .date(notification.getDate())
                            .status(notification.getStatus())
                            .message(notification.getMessage())
                            .build())
                    .toList();
            return new ApiResponse<>("Notifications found successfully", Estatus.SUCCESS, notificationResponseDtoList);
        }

    }

    @Override
    public ApiResponse<NotificationResponseDto> createNotification(NotificationRequestDto notificationRequestDto) {

        var notification = modelMapper.map(notificationRequestDto, Notification.class);
        notification.setCaregiver(caregiverRepository.getCaregiverById(notificationRequestDto.getCaregiver()));
        notification.setStatus(NotificationStatus.ENTREGADO);
        notification.setDate(LocalDateTime.now());
        notificationRepository.save(notification);

        NotificationResponseDto notificationResponseDto = NotificationResponseDto.builder()
                .id(notification.getId())
                .title(notification.getTitle())
                .caregiver(notification.getCaregiver().getId())
                .date(notification.getDate())
                .status(notification.getStatus())
                .message(notification.getMessage())
                .build();

        return new ApiResponse<>("Notification created successfully", Estatus.SUCCESS, notificationResponseDto);
    }

    @Override
    public ApiResponse<NotificationResponseDto> updateNotification(int id, UpdateNotificationStatus updateNotificationStatus) {
        Optional<Notification> optionalNotification = notificationRepository.findById(id);

        if (optionalNotification.isEmpty()){
            return new ApiResponse<>("Notification not found", Estatus.ERROR, null);
        } else {
            Notification notification = optionalNotification.get();
            notification.setStatus(updateNotificationStatus.getStatus());
            notificationRepository.save(notification);

            NotificationResponseDto notificationResponseDto = NotificationResponseDto.builder()
                    .id(notification.getId())
                    .title(notification.getTitle())
                    .caregiver(notification.getCaregiver().getId())
                    .date(notification.getDate())
                    .status(notification.getStatus())
                    .message(notification.getMessage())
                    .build();
            return new ApiResponse<>("Notification updated successfully", Estatus.SUCCESS, notificationResponseDto);
        }
    }

    @Override
    public ApiResponse<Void> deleteNotification(int id) {

        if (notificationRepository.existsById(id)){
            notificationRepository.deleteById(id);
            return new ApiResponse<>("Notification deleted successfully", Estatus.SUCCESS, null);
        } else {
            return new ApiResponse<>("Notification not found", Estatus.ERROR, null);
        }
    }

}
