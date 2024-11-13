package com.miam.cloudApi.miamCloudApi.application.controller;

import com.miam.cloudApi.miamCloudApi.application.dto.request.NotificationRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.request.UpdateNotificationStatus;
import com.miam.cloudApi.miamCloudApi.application.dto.response.NotificationResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.NotificationService;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Notification", description = "Notification API")
@RestController
@RequestMapping("/api/v1/miam/cloudApi")
public class NotificationController {

    NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Operation(summary = "get a notification by id")
    @GetMapping("/notifications/{id}")
    public ResponseEntity<ApiResponse<NotificationResponseDto>> getNotificationById(@PathVariable int id) {
        var res = notificationService.getNotification(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "get notifications by caregiver")
    @GetMapping("/notifications/caregiver/{caregiverId}")
    public ResponseEntity<ApiResponse<List<NotificationResponseDto>>> getNotificationsByCaregiver(@PathVariable int caregiverId) {
        var res = notificationService.getNotificationsByCaregiver(caregiverId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "create a new notification")
    @PostMapping("/notifications")
    public ResponseEntity<ApiResponse<NotificationResponseDto>> createNotification(@RequestBody NotificationRequestDto notificationRequestDto) {
        var res = notificationService.createNotification(notificationRequestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing notification")
    @PutMapping("/notifications/{id}")
    public ResponseEntity<ApiResponse<NotificationResponseDto>> updateNotification(@PathVariable int id, @RequestBody UpdateNotificationStatus updateNotificationStatus) {
        var res = notificationService.updateNotification(id, updateNotificationStatus);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a notification")
    @DeleteMapping("/notifications/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteNotification(@PathVariable int id) {
        var res = notificationService.deleteNotification(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
