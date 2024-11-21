package com.miam.cloudApi.miamCloudApi.domain.enums;

public enum ReportType {
    HIGH_HEART_RATE("High Heart Rate"),
    LOW_HEART_RATE("Low Heart Rate"),
    HIGH_TEMPERATURE("High Temperature"),
    LOW_TEMPERATURE("Low Temperature"),
    NORMAL_METRICS("Normal Metrics");

    private String type;

    ReportType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
