package com.miam.cloudApi.miamCloudApi.infraestructure.repositories;

import com.miam.cloudApi.miamCloudApi.domain.entities.PatientCaregiver;
import com.miam.cloudApi.miamCloudApi.application.serializable.ids.PatientCaregiverId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PatientCaregiverRepository extends CrudRepository<PatientCaregiver, PatientCaregiverId> {

    Optional<PatientCaregiver> findByPatientIdAndCaregiverId(int patientId, int caregiverId);

    List<PatientCaregiver> getPatientCaregiverByCaregiverId(int id);

    List<PatientCaregiver> getPatientCaregiverByPatientId(int id);

}
