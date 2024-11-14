package com.miam.cloudApi.miamCloudApi.infraestructure.repositories;

import com.miam.cloudApi.miamCloudApi.domain.entities.NursingHome;
import org.springframework.data.repository.CrudRepository;

public interface NursingHomeRepository extends CrudRepository<NursingHome, Integer> {

    NursingHome getNursingHomeById(int id);

}
