package com.miam.cloudApi.miamCloudApi.infraestructure.repositories;

import com.miam.cloudApi.miamCloudApi.domain.entities.Caregiver;
import org.springframework.data.repository.CrudRepository;

public interface CaregiverRepository extends CrudRepository<Caregiver, Integer> {

    Caregiver getCaregiverById(int id);

    Caregiver getCaregiverByAccountId(int id);

}
