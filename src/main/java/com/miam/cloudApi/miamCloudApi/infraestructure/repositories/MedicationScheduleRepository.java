package com.miam.cloudApi.miamCloudApi.infraestructure.repositories;

import com.miam.cloudApi.miamCloudApi.domain.entities.MedicationSchedule;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MedicationScheduleRepository extends CrudRepository<MedicationSchedule, Integer> {

    List<MedicationSchedule> getMedicationScheduleByPatientId(int patientId);

}
