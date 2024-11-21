package com.miam.cloudApi.miamCloudApi.application.services.impl;

import com.miam.cloudApi.miamCloudApi.application.client.MetricsClient;
import com.miam.cloudApi.miamCloudApi.application.dto.clientsDto.HeartRateResponseDto;
import com.miam.cloudApi.miamCloudApi.application.dto.clientsDto.TemperatureResponseDto;
import com.miam.cloudApi.miamCloudApi.application.dto.request.NotificationRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.request.ReportHistoryRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.request.UpdateHistoryRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.request.UpdateReportHistoryStatus;
import com.miam.cloudApi.miamCloudApi.application.dto.response.ReportHistoryResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.NotificationService;
import com.miam.cloudApi.miamCloudApi.application.services.ReportHistoryService;
import com.miam.cloudApi.miamCloudApi.domain.entities.ReportHistory;
import com.miam.cloudApi.miamCloudApi.domain.enums.ReportType;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.CaregiverRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.NotificationRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.PatientRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.ReportHistoryRepository;
import com.miam.cloudApi.security.storage.JwtTokenStorage;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import com.miam.cloudApi.shared.model.enums.Estatus;
import lombok.extern.java.Log;
import org.hibernate.sql.Update;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReportHistoryServiceImpl implements ReportHistoryService {

    private static final Logger log = LoggerFactory.getLogger(ReportHistoryServiceImpl.class);
    ReportHistoryRepository reportHistoryRepository;
    PatientRepository patientRepository;
    CaregiverRepository caregiverRepository;
    NotificationService notificationService;
    MetricsClient metricsClient;
    ModelMapper modelMapper;
    private final JwtTokenStorage jwtTokenStorage;

    public ReportHistoryServiceImpl(ReportHistoryRepository reportHistoryRepository, PatientRepository patientRepository, CaregiverRepository caregiverRepository, ModelMapper modelMapper, NotificationService notificationService, MetricsClient metricsClient, JwtTokenStorage jwtTokenStorage){
        this.reportHistoryRepository = reportHistoryRepository;
        this.patientRepository = patientRepository;
        this.caregiverRepository = caregiverRepository;
        this.modelMapper = modelMapper;
        this.notificationService = notificationService;
        this.metricsClient = metricsClient;
        this.jwtTokenStorage = jwtTokenStorage;
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
                    .reportType(reportHistory.getReportType())
                    .generatedDate(reportHistory.getGeneratedDate())
                    .attendingDate(reportHistory.getAttendingDate())
                    .description(reportHistory.getDescription())
                    .caregiverNotes(reportHistory.getCaregiverNotes())
                    .actions(reportHistory.getActions())
                    .patientId(reportHistory.getPatient().getId())
                    .caregiverId(reportHistory.getCaregiver().getId())
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
                            .reportType(reportHistory.getReportType())
                            .generatedDate(reportHistory.getGeneratedDate())
                            .attendingDate(reportHistory.getAttendingDate())
                            .description(reportHistory.getDescription())
                            .caregiverNotes(reportHistory.getCaregiverNotes())
                            .actions(reportHistory.getActions())
                            .patientId(reportHistory.getPatient().getId())
                            .caregiverId(reportHistory.getCaregiver().getId())
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
                            .reportType(reportHistory.getReportType())
                            .generatedDate(reportHistory.getGeneratedDate())
                            .attendingDate(reportHistory.getAttendingDate())
                            .description(reportHistory.getDescription())
                            .caregiverNotes(reportHistory.getCaregiverNotes())
                            .actions(reportHistory.getActions())
                            .patientId(reportHistory.getPatient().getId())
                            .caregiverId(reportHistory.getCaregiver().getId())
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
        reportHistory.setGeneratedDate(LocalDateTime.now());

        if (reportHistoryRequestDto.getTemperature() <= 35 ) {
            reportHistory.setReportType(ReportType.LOW_TEMPERATURE);

            NotificationRequestDto notificationRequestDto = new NotificationRequestDto();
            notificationRequestDto.setTitle(reportHistory.getReportType().toString());
            notificationRequestDto.setMessage("Patient " + reportHistory.getPatient().getName() + " has a low temperature");
            notificationRequestDto.setCaregiver(reportHistoryRequestDto.getCaregiverId());

            notificationService.createNotification(notificationRequestDto);

        } else if (reportHistoryRequestDto.getTemperature() >= 38) {
            reportHistory.setReportType(ReportType.HIGH_TEMPERATURE);

            NotificationRequestDto notificationRequestDto = new NotificationRequestDto();
            notificationRequestDto.setTitle(reportHistory.getReportType().toString());
            notificationRequestDto.setMessage("Patient " + reportHistory.getPatient().getName() + " has a high temperature");
            notificationRequestDto.setCaregiver(reportHistoryRequestDto.getCaregiverId());

            notificationService.createNotification(notificationRequestDto);
        }

        if (reportHistoryRequestDto.getHeartRate() <= 60){
            reportHistory.setReportType(ReportType.LOW_HEART_RATE);

            NotificationRequestDto notificationRequestDto = new NotificationRequestDto();
            notificationRequestDto.setTitle(reportHistory.getReportType().toString());
            notificationRequestDto.setMessage("Patient " + reportHistory.getPatient().getName() + " has a low heart rate");
            notificationRequestDto.setCaregiver(reportHistoryRequestDto.getCaregiverId());

            notificationService.createNotification(notificationRequestDto);

        } else if (reportHistoryRequestDto.getHeartRate() >= 100){
            reportHistory.setReportType(ReportType.HIGH_HEART_RATE);

            NotificationRequestDto notificationRequestDto = new NotificationRequestDto();
            notificationRequestDto.setTitle(reportHistory.getReportType().toString());
            notificationRequestDto.setMessage("Patient " + reportHistory.getPatient().getName() + " has a high heart rate");
            notificationRequestDto.setCaregiver(reportHistoryRequestDto.getCaregiverId());

            notificationService.createNotification(notificationRequestDto);
        }

        if (reportHistory.getReportType() == null){
            reportHistory.setReportType(ReportType.NORMAL_METRICS);
        }

        reportHistoryRepository.save(reportHistory);

        ReportHistoryResponseDto reportHistoryResponse = ReportHistoryResponseDto.builder()
                .id(reportHistory.getId())
                .reportType(reportHistory.getReportType())
                .generatedDate(reportHistory.getGeneratedDate())
                .attendingDate(reportHistory.getAttendingDate())
                .description(reportHistory.getDescription())
                .caregiverNotes(reportHistory.getCaregiverNotes())
                .actions(reportHistory.getActions())
                .patientId(reportHistory.getPatient().getId())
                .caregiverId(reportHistory.getCaregiver().getId())
                .build();

        return new ApiResponse<>("Report history created successfully", Estatus.SUCCESS, reportHistoryResponse);
    }

    @Override
    public ApiResponse<ReportHistoryResponseDto> updateReportHistory(int id, UpdateHistoryRequestDto updateHistoryRequestDto) {

        Optional<ReportHistory> optionalReportHistory = reportHistoryRepository.findById(id);

        if (optionalReportHistory.isEmpty()) {
            return new ApiResponse<>("Report history not found", Estatus.ERROR, null);
        } else {
            ReportHistory reportHistory = optionalReportHistory.get();
            reportHistory.setAttendingDate(updateHistoryRequestDto.getAttendingDate());
            reportHistory.setDescription(updateHistoryRequestDto.getDescription());
            reportHistory.setCaregiverNotes(updateHistoryRequestDto.getCaregiverNotes());
            reportHistory.setActions(updateHistoryRequestDto.getActions());
            reportHistoryRepository.save(reportHistory);

            ReportHistoryResponseDto reportHistoryResponse = ReportHistoryResponseDto.builder()
                    .id(reportHistory.getId())
                    .reportType(reportHistory.getReportType())
                    .generatedDate(reportHistory.getGeneratedDate())
                    .attendingDate(reportHistory.getAttendingDate())
                    .description(reportHistory.getDescription())
                    .caregiverNotes(reportHistory.getCaregiverNotes())
                    .actions(reportHistory.getActions())
                    .patientId(reportHistory.getPatient().getId())
                    .caregiverId(reportHistory.getCaregiver().getId())
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

    @Override
    @Scheduled(fixedRate = 60000)
    public void autoCreateReportHistory() {

        if (jwtTokenStorage == null) {
            System.console().printf("No token found");
        } else if (jwtTokenStorage != null) {
            if (jwtTokenStorage.getToken() == null) {
                log.error("Token not found");
            } else {
                log.info("Token found");
                ReportHistoryRequestDto reportHistoryRequestDto = new ReportHistoryRequestDto();
                reportHistoryRequestDto.setPatientId(1);
                reportHistoryRequestDto.setCaregiverId(15);

                ApiResponse<TemperatureResponseDto> temperature = metricsClient.getTemperature(1);
                Double tmp = temperature.getData().getTemperature();

                reportHistoryRequestDto.setTemperature(tmp);

                ApiResponse<HeartRateResponseDto> heartRate = metricsClient.getHeartRate(1);

                int hr = heartRate.getData().getHeartRate();

                reportHistoryRequestDto.setHeartRate(hr);

                createReportHistory(reportHistoryRequestDto);
            }

        }
    }

}
