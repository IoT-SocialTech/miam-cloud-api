package com.miam.cloudApi.miamCloudApi.infraestructure.repositories;

import com.miam.cloudApi.miamCloudApi.domain.entities.Subscription;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {

    Subscription getSubscriptionById(int id);

}
