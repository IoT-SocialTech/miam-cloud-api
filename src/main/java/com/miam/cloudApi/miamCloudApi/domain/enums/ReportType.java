package com.miam.cloudApi.miamCloudApi.domain.enums;

public enum ReportType {
    HEART_RATE("Heart Rate"),
    TEMPERATURE("Temperature"),
    ALERTS_GENERATED("Alerts Generated");

    private String type;

    ReportType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
