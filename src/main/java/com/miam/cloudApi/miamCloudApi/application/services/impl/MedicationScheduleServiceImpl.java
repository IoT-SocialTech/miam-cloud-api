package com.miam.cloudApi.miamCloudApi.application.services.impl;

import com.miam.cloudApi.miamCloudApi.application.dto.request.MedicationScheduleRequest;
import com.miam.cloudApi.miamCloudApi.application.dto.request.UpdateMedicationScheduleTaken;
import com.miam.cloudApi.miamCloudApi.application.dto.response.MedicationScheduleResponse;
import com.miam.cloudApi.miamCloudApi.application.services.MedicationScheduleService;
import com.miam.cloudApi.miamCloudApi.domain.entities.MedicationSchedule;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.CaregiverRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.MedicationScheduleRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.PatientRepository;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import com.miam.cloudApi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationScheduleServiceImpl implements MedicationScheduleService {

    MedicationScheduleRepository medicationScheduleRepository;
    PatientRepository patientRepository;
    CaregiverRepository caregiverRepository;
    ModelMapper modelMapper;

    public MedicationScheduleServiceImpl(MedicationScheduleRepository medicationScheduleRepository, PatientRepository patientRepository, CaregiverRepository caregiverRepository, ModelMapper modelMapper){
        this.medicationScheduleRepository = medicationScheduleRepository;
        this.patientRepository = patientRepository;
        this.caregiverRepository = caregiverRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<MedicationScheduleResponse> getMedicationScheduleById(int id) {

        Optional<MedicationSchedule> optionalMedicationSchedule = medicationScheduleRepository.findById(id);

        if (optionalMedicationSchedule.isEmpty()) {
            return new ApiResponse<>("Medication schedule not found", Estatus.ERROR, null);
        } else {
            MedicationSchedule medicationSchedule = optionalMedicationSchedule.get();
            MedicationScheduleResponse medicationScheduleResponse = MedicationScheduleResponse.builder()
                    .id(medicationSchedule.getId())
                    .patientId(medicationSchedule.getPatient().getId())
                    .caregiverId(medicationSchedule.getCaregiver().getId())
                    .medicationName(medicationSchedule.getMedicationName())
                    .dose(medicationSchedule.getDose())
                    .hour(medicationSchedule.getHour())
                    .taken(medicationSchedule.getTaken())
                    .build();
            return new ApiResponse<>("Medication schedule found successfully", Estatus.SUCCESS, medicationScheduleResponse);
        }

    }

    @Override
    public ApiResponse<List<MedicationScheduleResponse>> getMedicationScheduleByPatientId(int patientId) {

        List<MedicationSchedule> medicationScheduleList = medicationScheduleRepository.getMedicationScheduleByPatientId(patientId);

        if (medicationScheduleList.isEmpty()) {
            return new ApiResponse<>("No medication schedule found for this patient", Estatus.ERROR, null);
        } else {
            List<MedicationScheduleResponse> medicationScheduleResponseList = medicationScheduleList.stream()
                    .map(medicationSchedule -> MedicationScheduleResponse.builder()
                            .id(medicationSchedule.getId())
                            .patientId(medicationSchedule.getPatient().getId())
                            .caregiverId(medicationSchedule.getCaregiver().getId())
                            .medicationName(medicationSchedule.getMedicationName())
                            .dose(medicationSchedule.getDose())
                            .hour(medicationSchedule.getHour())
                            .taken(medicationSchedule.getTaken())
                            .build())
                    .toList();
            return new ApiResponse<>("Medication schedule found successfully", Estatus.SUCCESS, medicationScheduleResponseList);
        }

    }

    @Override
    public ApiResponse<MedicationScheduleResponse> createMedicationSchedule(MedicationScheduleRequest medicationScheduleRequest) {

        MedicationSchedule medicationSchedule = new MedicationSchedule();
        medicationSchedule.setMedicationName(medicationScheduleRequest.getMedicationName());
        medicationSchedule.setDose(medicationScheduleRequest.getDose());
        medicationSchedule.setHour(medicationScheduleRequest.getHour());
        medicationSchedule.setTaken(medicationScheduleRequest.getTaken());
        medicationSchedule.setPatient(patientRepository.getPatientsById(medicationScheduleRequest.getPatientId()));
        medicationSchedule.setCaregiver(caregiverRepository.getCaregiverById(medicationScheduleRequest.getCaregiverId()));
        medicationScheduleRepository.save(medicationSchedule);

        MedicationScheduleResponse medicationScheduleResponse = MedicationScheduleResponse.builder()
                .id(medicationSchedule.getId())
                .patientId(medicationSchedule.getPatient().getId())
                .caregiverId(medicationSchedule.getCaregiver().getId())
                .medicationName(medicationSchedule.getMedicationName())
                .dose(medicationSchedule.getDose())
                .hour(medicationSchedule.getHour())
                .taken(medicationSchedule.getTaken())
                .build();

        return new ApiResponse<>("Medication schedule created successfully", Estatus.SUCCESS, medicationScheduleResponse);

    }

    @Override
    public ApiResponse<MedicationScheduleResponse> updateMedicationSchedule(int id, UpdateMedicationScheduleTaken updateMedicationScheduleTaken) {

        Optional<MedicationSchedule> optionalMedicationSchedule = medicationScheduleRepository.findById(id);

        if (optionalMedicationSchedule.isEmpty()) {
            return new ApiResponse<>("Medication schedule not found", Estatus.ERROR, null);
        } else {
            MedicationSchedule medicationSchedule = optionalMedicationSchedule.get();
            medicationSchedule.setTaken(updateMedicationScheduleTaken.getTaken());
            medicationScheduleRepository.save(medicationSchedule);

            MedicationScheduleResponse medicationScheduleResponse = MedicationScheduleResponse.builder()
                    .id(medicationSchedule.getId())
                    .patientId(medicationSchedule.getPatient().getId())
                    .caregiverId(medicationSchedule.getCaregiver().getId())
                    .medicationName(medicationSchedule.getMedicationName())
                    .dose(medicationSchedule.getDose())
                    .hour(medicationSchedule.getHour())
                    .taken(medicationSchedule.getTaken())
                    .build();

            return new ApiResponse<>("Medication schedule updated successfully", Estatus.SUCCESS, medicationScheduleResponse);
        }

    }

    @Override
    public ApiResponse<Void> deleteMedicationSchedule(int id) {


        if (medicationScheduleRepository.existsById(id)) {
            medicationScheduleRepository.deleteById(id);
            return new ApiResponse<>("Medication schedule deleted successfully", Estatus.SUCCESS, null);
        } else {
            return new ApiResponse<>("Medication schedule not found", Estatus.ERROR, null);
        }

    }


}
