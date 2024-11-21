package com.miam.cloudApi.miamCloudApi.application.services;

import com.miam.cloudApi.miamCloudApi.application.dto.request.ReportHistoryRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.request.UpdateHistoryRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.request.UpdateReportHistoryStatus;
import com.miam.cloudApi.miamCloudApi.application.dto.response.ReportHistoryResponseDto;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface ReportHistoryService {

    ApiResponse<ReportHistoryResponseDto> getReportHistoryById(int id);

    ApiResponse<List<ReportHistoryResponseDto>> getReportHistoryByPatientId(int patientId);

    ApiResponse<List<ReportHistoryResponseDto>> getReportHistoryByCaregiverId(int caregiverId);

    ApiResponse<ReportHistoryResponseDto> createReportHistory(ReportHistoryRequestDto reportHistoryRequestDto);

    ApiResponse<ReportHistoryResponseDto> updateReportHistory(int id, UpdateHistoryRequestDto updateHistoryRequestDto);

    ApiResponse<Void> deleteReportHistory(int id);

    void autoCreateReportHistory();

}
