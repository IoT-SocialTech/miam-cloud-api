package com.miam.cloudApi.miamCloudApi.application.dto.response;

import com.miam.cloudApi.miamCloudApi.domain.enums.ReportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportHistoryResponseDto {

    private int id;

    private ReportType reportType;

    private LocalDateTime generatedDate;

    private String status;

    private int patientId;

    private int caregiverId;

}
