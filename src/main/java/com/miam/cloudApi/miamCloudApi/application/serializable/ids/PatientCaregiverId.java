package com.miam.cloudApi.miamCloudApi.application.serializable.ids;

import java.io.Serializable;
import java.util.Objects;

public class PatientCaregiverId implements Serializable {
    private int patient;
    private int caregiver;


    public PatientCaregiverId() {}

    public PatientCaregiverId(int patient, int caregiver) {
        this.patient = patient;
        this.caregiver = caregiver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientCaregiverId that = (PatientCaregiverId) o;
        return patient == that.patient && caregiver == that.caregiver;
    }

    @Override
    public int hashCode() {
        return Objects.hash(patient, caregiver);
    }
}