package com.miam.cloudApi.miamCloudApi.application.controller;

import com.miam.cloudApi.miamCloudApi.application.dto.request.RelativeRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.RelativeResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.RelativeService;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Relative", description = "Relative API")
@RestController
@RequestMapping("/api/v1/miam/cloudApi")
public class RelativeController {

    private final RelativeService relativeService;

    public RelativeController(RelativeService relativeService) {
        this.relativeService = relativeService;
    }

    @Operation(summary = "Get relative by id")
    @GetMapping("/relatives/{id}")
    public ResponseEntity<ApiResponse<RelativeResponseDto>> getRelativeById(@PathVariable int id) {
        var res = relativeService.getRelativeById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Get relative by account id")
    @GetMapping("/relatives/account/{id}")
    public ResponseEntity<ApiResponse<RelativeResponseDto>> getRelativeByAccountId(@PathVariable int id) {
        var res = relativeService.getRelativeByAccountId(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Create relative")
    @PostMapping("/relatives")
    public ResponseEntity<ApiResponse<RelativeResponseDto>> createRelative(@RequestBody RelativeRequestDto relativeRequestDto) {
        var res = relativeService.createRelative(relativeRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update relative")
    @PutMapping("/relatives/{id}")
    public ResponseEntity<ApiResponse<RelativeResponseDto>> updateRelative(@PathVariable int id, @RequestBody RelativeRequestDto relativeRequestDto) {
        var res = relativeService.updateRelative(id, relativeRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Delete relative")
    @DeleteMapping("/relatives/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRelative(@PathVariable int id) {
        var res = relativeService.deleteRelative(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
