package com.miam.cloudApi.miamCloudApi.infraestructure.repositories;

import com.miam.cloudApi.miamCloudApi.domain.entities.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    Account getAccountById(int id);

    Optional<Account> findAccountByEmail(String email);

    Boolean existsAccountByEmail(String email);

}
