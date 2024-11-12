package com.miam.cloudApi.miamCloudApi.domain.entities;

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
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "start_date")
    LocalDateTime startDate;

    @Column(name = "end_date")
    LocalDateTime endDate;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "type", length = 100)
    private String type;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

}
