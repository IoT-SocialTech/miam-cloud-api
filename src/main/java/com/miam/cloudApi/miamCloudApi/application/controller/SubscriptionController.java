package com.miam.cloudApi.miamCloudApi.application.controller;

import com.miam.cloudApi.miamCloudApi.application.dto.request.SubscriptionRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.SubscriptionResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.SubscriptionService;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Subscription Controller", description = "Subscription Controller API")
@RestController
@RequestMapping("/api/v1/miam-cloud-api")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Operation(summary = "Get subscription by id")
    @GetMapping("/subscriptions/{id}")
    public ResponseEntity<ApiResponse<SubscriptionResponseDto>> getSubscriptionById(@PathVariable int id) {
        var res = subscriptionService.getSubscriptionById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Create subscription")
    @PostMapping("/subscriptions")
    public ResponseEntity<ApiResponse<SubscriptionResponseDto>> createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        var res = subscriptionService.createSubscription(subscriptionRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update subscription")
    @PutMapping("/subscriptions/{id}")
    public ResponseEntity<ApiResponse<SubscriptionResponseDto>> updateSubscription(@PathVariable int id, @RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        var res = subscriptionService.updateSubscription(id, subscriptionRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Delete subscription")
    @DeleteMapping("/subscriptions/{id}")
    public ResponseEntity<ApiResponse<SubscriptionResponseDto>> deleteSubscription(@PathVariable int id) {
        var res = subscriptionService.deleteSubscription(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
