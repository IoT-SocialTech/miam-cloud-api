package com.miam.cloudApi.miamCloudApi.domain.entities;

import com.miam.cloudApi.miamCloudApi.domain.enums.NotificationStatus;
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
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", length = 150, nullable = false)
    private String title;

    @Column(name = "message", length = 150, nullable = false)
    private String message;

    @Column(name = "status", length = 150, nullable = false)
    private NotificationStatus status;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "caregiver_id", nullable = false)
    private Caregiver caregiver;

}
