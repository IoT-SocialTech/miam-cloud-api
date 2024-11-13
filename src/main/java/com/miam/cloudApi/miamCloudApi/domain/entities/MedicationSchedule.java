package com.miam.cloudApi.miamCloudApi.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "medication_schedule")
public class MedicationSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "medication_name", length = 50, nullable = false)
    private String medicationName;

    @Column(name = "dose", length = 50, nullable = false)
    private int dose;

    @Column(name = "hour", length = 50, nullable = false)
    private LocalTime hour;

    @Column(name = "taken", nullable = false)
    private Boolean taken;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "caregiver_id", nullable = false)
    private Caregiver caregiver;

}
