package com.miam.cloudApi.miamCloudApi.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "relatives")
public class Relative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 75, nullable = false)
    private String name;

    @Column(name = "last_name", length = 75, nullable = false)
    private String lastName;

    @Column(name = "relationship", length = 50, nullable = false)
    private String relationship;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @OneToOne(mappedBy = "relative")
    @JsonBackReference
    private Patient patient;

}
