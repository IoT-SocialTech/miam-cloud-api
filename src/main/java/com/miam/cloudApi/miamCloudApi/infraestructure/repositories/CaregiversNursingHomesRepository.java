package com.miam.cloudApi.miamCloudApi.infraestructure.repositories;

import com.miam.cloudApi.miamCloudApi.application.serializable.ids.CaregiversNursingHomesId;
import com.miam.cloudApi.miamCloudApi.domain.entities.Caregiver;
import com.miam.cloudApi.miamCloudApi.domain.entities.CaregiversNursingHomes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CaregiversNursingHomesRepository extends CrudRepository<CaregiversNursingHomes, CaregiversNursingHomesId> {

    Optional<CaregiversNursingHomes> findByCaregiverIdAndNursingHomeId(int caregiverId, int nursingHomeId);

    List<CaregiversNursingHomes> getCaregiversNursingHomesByNursingHomeId(int id);

}
