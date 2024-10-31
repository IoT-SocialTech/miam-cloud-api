package com.miam.cloudApi.miamCloudApi.domain.entities;

import com.miam.cloudApi.miamCloudApi.application.serializable.ids.PatientCaregiverId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "patient_caregiver")
@IdClass(PatientCaregiverId.class)
public class PatientCaregiver {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "caregiver_id", nullable = false)
    private Caregiver caregiver;

}