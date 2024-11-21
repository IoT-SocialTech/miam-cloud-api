package com.miam.cloudApi.miamCloudApi.domain.entities;

import com.miam.cloudApi.miamCloudApi.domain.enums.ReportType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "report_history")
public class ReportHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "report_type", length = 50, nullable = false)
    private ReportType reportType;

    @Column(name = "generated_date", nullable = false)
    private LocalDateTime generatedDate;

    @Column(name = "attending_date")
    private LocalDateTime attendingDate;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "caregiver_notes", length = 255)
    private String caregiverNotes;

    @Column(name = "actions", length = 255)
    private String actions;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "caregiver_id", nullable = false)
    private Caregiver caregiver;

}
