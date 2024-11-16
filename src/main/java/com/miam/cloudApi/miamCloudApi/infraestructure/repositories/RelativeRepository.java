package com.miam.cloudApi.miamCloudApi.infraestructure.repositories;

import com.miam.cloudApi.miamCloudApi.domain.entities.Relative;
import org.springframework.data.repository.CrudRepository;

public interface RelativeRepository extends CrudRepository<Relative, Integer> {

    Relative getRelativesById(int id);

    Relative getRelativeByAccountId(int id);

}
