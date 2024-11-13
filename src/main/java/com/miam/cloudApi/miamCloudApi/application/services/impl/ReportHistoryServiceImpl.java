package com.miam.cloudApi.miamCloudApi.application.services.impl;

import com.miam.cloudApi.miamCloudApi.application.dto.request.ReportHistoryRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.request.UpdateReportHistoryStatus;
import com.miam.cloudApi.miamCloudApi.application.dto.response.ReportHistoryResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.ReportHistoryService;
import com.miam.cloudApi.miamCloudApi.domain.entities.ReportHistory;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.CaregiverRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.PatientRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.ReportHistoryRepository;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import com.miam.cloudApi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReportHistoryServiceImpl implements ReportHistoryService {

    ReportHistoryRepository reportHistoryRepository;
    PatientRepository patientRepository;
    CaregiverRepository caregiverRepository;
    ModelMapper modelMapper;

    public ReportHistoryServiceImpl(ReportHistoryRepository reportHistoryRepository, PatientRepository patientRepository, CaregiverRepository caregiverRepository, ModelMapper modelMapper){
        this.reportHistoryRepository = reportHistoryRepository;
        this.patientRepository = patientRepository;
        this.caregiverRepository = caregiverRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<ReportHistoryResponseDto> getReportHistoryById(int id) {

        Optional<ReportHistory> optionalReportHistory = reportHistoryRepository.findById(id);

        if (optionalReportHistory.isEmpty()) {
            return new ApiResponse<>("Report history not found", Estatus.ERROR, null);
        } else {
            ReportHistory reportHistory = optionalReportHistory.get();
            ReportHistoryResponseDto reportHistoryResponse = ReportHistoryResponseDto.builder()
                    .id(reportHistory.getId())
                    .patientId(reportHistory.getPatient().getId())
                    .caregiverId(reportHistory.getCaregiver().getId())
                    .generatedDate(LocalDateTime.now())
                    .reportType(reportHistory.getReportType())
                    .status(reportHistory.getStatus())
                    .build();
            return new ApiResponse<>("Report history found successfully", Estatus.SUCCESS, reportHistoryResponse);
        }

    }

    @Override
    public ApiResponse<List<ReportHistoryResponseDto>> getReportHistoryByPatientId(int patientId) {

        List<ReportHistory> reportHistoryList = reportHistoryRepository.getReportHistoryByPatientId(patientId);

        if (reportHistoryList.isEmpty()) {
            return new ApiResponse<>("Report history not found", Estatus.ERROR, null);
        } else {
            List<ReportHistoryResponseDto> reportHistoryResponseList = reportHistoryList.stream()
                    .map(reportHistory -> ReportHistoryResponseDto.builder()
                            .id(reportHistory.getId())
                            .patientId(reportHistory.getPatient().getId())
                            .caregiverId(reportHistory.getCaregiver().getId())
                            .generatedDate(reportHistory.getGeneratedDate())
                            .reportType(reportHistory.getReportType())
                            .status(reportHistory.getStatus())
                            .build())
                    .toList();
            return new ApiResponse<>("Report history found successfully", Estatus.SUCCESS, reportHistoryResponseList);
        }

    }

    @Override
    public ApiResponse<List<ReportHistoryResponseDto>> getReportHistoryByCaregiverId(int caregiverId) {

        List<ReportHistory> reportHistoryList = reportHistoryRepository.getReportHistoryByCaregiverId(caregiverId);

        if (reportHistoryList.isEmpty()) {
            return new ApiResponse<>("Report history not found", Estatus.ERROR, null);
        } else {
            List<ReportHistoryResponseDto> reportHistoryResponseList = reportHistoryList.stream()
                    .map(reportHistory -> ReportHistoryResponseDto.builder()
                            .id(reportHistory.getId())
                            .patientId(reportHistory.getPatient().getId())
                            .caregiverId(reportHistory.getCaregiver().getId())
                            .generatedDate(reportHistory.getGeneratedDate())
                            .reportType(reportHistory.getReportType())
                            .status(reportHistory.getStatus())
                            .build())
                    .toList();
            return new ApiResponse<>("Report history found successfully", Estatus.SUCCESS, reportHistoryResponseList);
        }

    }

    @Override
    public ApiResponse<ReportHistoryResponseDto> createReportHistory(ReportHistoryRequestDto reportHistoryRequestDto) {

        ReportHistory reportHistory = new ReportHistory();
        reportHistory.setPatient(patientRepository.getPatientsById(reportHistoryRequestDto.getPatientId()));
        reportHistory.setCaregiver(caregiverRepository.getCaregiverById(reportHistoryRequestDto.getCaregiverId()));
        reportHistory.setReportType(reportHistoryRequestDto.getReportType());
        reportHistory.setStatus(reportHistoryRequestDto.getStatus());
        reportHistory.setGeneratedDate(LocalDateTime.now());
        reportHistoryRepository.save(reportHistory);

        ReportHistoryResponseDto reportHistoryResponse = ReportHistoryResponseDto.builder()
                .id(reportHistory.getId())
                .patientId(reportHistory.getPatient().getId())
                .caregiverId(reportHistory.getCaregiver().getId())
                .generatedDate(LocalDateTime.now())
                .reportType(reportHistory.getReportType())
                .status(reportHistory.getStatus())
                .build();

        return new ApiResponse<>("Report history created successfully", Estatus.SUCCESS, reportHistoryResponse);
    }

    @Override
    public ApiResponse<ReportHistoryResponseDto> updateReportHistory(int id, UpdateReportHistoryStatus updateReportHistoryStatus) {

        Optional<ReportHistory> optionalReportHistory = reportHistoryRepository.findById(id);

        if (optionalReportHistory.isEmpty()) {
            return new ApiResponse<>("Report history not found", Estatus.ERROR, null);
        } else {
            ReportHistory reportHistory = optionalReportHistory.get();
            reportHistory.setStatus(updateReportHistoryStatus.getStatus());
            reportHistoryRepository.save(reportHistory);

            ReportHistoryResponseDto reportHistoryResponse = ReportHistoryResponseDto.builder()
                    .id(reportHistory.getId())
                    .patientId(reportHistory.getPatient().getId())
                    .caregiverId(reportHistory.getCaregiver().getId())
                    .generatedDate(reportHistory.getGeneratedDate())
                    .reportType(reportHistory.getReportType())
                    .status(reportHistory.getStatus())
                    .build();
            return new ApiResponse<>("Report history updated successfully", Estatus.SUCCESS, reportHistoryResponse);
        }

    }

    @Override
    public ApiResponse<Void> deleteReportHistory(int id) {

        if (reportHistoryRepository.existsById(id)) {
            reportHistoryRepository.deleteById(id);
            return new ApiResponse<>("Report history deleted successfully", Estatus.SUCCESS, null);
        } else {
            return new ApiResponse<>("Report history not found", Estatus.ERROR, null);
        }

    }

}
