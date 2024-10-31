package com.miam.cloudApi.miamCloudApi.application.services.impl;

import com.miam.cloudApi.miamCloudApi.application.dto.response.PatientCaregiverResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.PatientCaregiverService;
import com.miam.cloudApi.miamCloudApi.domain.entities.PatientCaregiver;
import com.miam.cloudApi.miamCloudApi.application.serializable.ids.PatientCaregiverId;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.CaregiverRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.PatientCaregiverRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.PatientRepository;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import com.miam.cloudApi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientCaregiverServiceImpl implements PatientCaregiverService {

    private final ModelMapper modelMapper;
    private final PatientRepository patientRepository;
    private final PatientCaregiverRepository patientCaregiverRepository;
    private final CaregiverRepository caregiverRepository;

    public PatientCaregiverServiceImpl(ModelMapper modelMapper, PatientRepository patientRepository, PatientCaregiverRepository patientCaregiverRepository, CaregiverRepository caregiverRepository) {
        this.modelMapper = modelMapper;
        this.patientRepository = patientRepository;
        this.patientCaregiverRepository = patientCaregiverRepository;
        this.caregiverRepository = caregiverRepository;
    }


    @Override
    public ApiResponse<PatientCaregiverResponseDto> getPatientCaregiverById(int patient, int caregiver) {

        Optional<PatientCaregiver> optionalPatientCaregiver = patientCaregiverRepository.findById(new PatientCaregiverId(patient, caregiver));

        if (optionalPatientCaregiver.isPresent()) {
            PatientCaregiverResponseDto patientCaregiverResponseDto = PatientCaregiverResponseDto.builder()
                    .patient(patient)
                    .caregiver(caregiver)
                    .build();
            return new ApiResponse<>("PatientCaregiver found successfully", Estatus.SUCCESS, patientCaregiverResponseDto);
        } else {
            return new ApiResponse<>("PatientCaregiver not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<PatientCaregiverResponseDto> createPatientCaregiver(int patient, int caregiver) {

        PatientCaregiver patientCaregiver = PatientCaregiver.builder()
                .patient(patientRepository.getPatientsById(patient))
                .caregiver(caregiverRepository.getCaregiverById(caregiver))
                .build();

        patientCaregiverRepository.save(patientCaregiver);

        PatientCaregiverResponseDto patientCaregiverResponseDto = PatientCaregiverResponseDto.builder()
                .patient(patient)
                .caregiver(caregiver)
                .build();

        return new ApiResponse<>("PatientCaregiver created successfully", Estatus.SUCCESS, patientCaregiverResponseDto);
    }

}