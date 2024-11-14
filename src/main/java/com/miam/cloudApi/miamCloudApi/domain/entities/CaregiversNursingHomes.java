package com.miam.cloudApi.miamCloudApi.domain.entities;

import com.miam.cloudApi.miamCloudApi.application.serializable.ids.CaregiversNursingHomesId;
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
@Table(name = "caregivers_nursing_homes")
@IdClass(CaregiversNursingHomesId.class)
public class CaregiversNursingHomes {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "caregiver_id", nullable = false)
    private Caregiver caregiver;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nursing_home_id", nullable = false)
    private NursingHome nursingHome;

}
