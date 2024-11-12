package com.miam.cloudApi.miamCloudApi.infraestructure.repositories;

import com.miam.cloudApi.miamCloudApi.domain.entities.Plan;
import org.springframework.data.repository.CrudRepository;

public interface PlanRepository extends CrudRepository<Plan, Integer> {

    Plan getPlanById(int id);

}
