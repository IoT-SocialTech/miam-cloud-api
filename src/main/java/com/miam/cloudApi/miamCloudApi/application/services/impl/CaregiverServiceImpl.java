package com.miam.cloudApi.miamCloudApi.application.services.impl;

import com.miam.cloudApi.miamCloudApi.application.dto.request.CaregiverRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.CaregiverResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.CaregiverService;
import com.miam.cloudApi.miamCloudApi.domain.entities.Caregiver;
import com.miam.cloudApi.miamCloudApi.domain.entities.Patient;
import com.miam.cloudApi.miamCloudApi.domain.entities.PatientCaregiver;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.AccountRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.CaregiverRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.PatientCaregiverRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.PatientRepository;
import com.miam.cloudApi.shared.exception.ResourceNotFoundException;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import com.miam.cloudApi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CaregiverServiceImpl implements CaregiverService {

    private final CaregiverRepository caregiverRepository;
    private final AccountRepository accountRepository;
    private final PatientRepository patientRepository;
    private final PatientCaregiverRepository patientCaregiverRepository;
    private final ModelMapper modelMapper;

    public CaregiverServiceImpl(CaregiverRepository caregiverRepository, AccountRepository accountRepository,
                                PatientRepository patientRepository, PatientCaregiverRepository patientCaregiverRepository,
                                ModelMapper modelMapper) {
        this.caregiverRepository = caregiverRepository;
        this.accountRepository = accountRepository;
        this.patientRepository = patientRepository;
        this.patientCaregiverRepository = patientCaregiverRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<CaregiverResponseDto> getCaregiverById(int id) {
        Optional<Caregiver> caregiverOptional = caregiverRepository.findById(id);

        if (caregiverOptional.isEmpty()) {
            return new ApiResponse<>("Caregiver not found", Estatus.ERROR, null);
        } else {
            Caregiver caregiver = caregiverOptional.get();
            CaregiverResponseDto response = modelMapper.map(caregiver, CaregiverResponseDto.class);
            return new ApiResponse<>("Caregiver found successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<CaregiverResponseDto> createCaregiver(CaregiverRequestDto caregiverRequestDto) {

        var caregiver = modelMapper.map(caregiverRequestDto, Caregiver.class);
        caregiver.setAccount(accountRepository.getAccountById(caregiverRequestDto.getAccount()));
        caregiver = caregiverRepository.save(caregiver);

        // Crear relaciones en PatientCaregiver si hay IDs de pacientes
        if (caregiverRequestDto.getPatientIds() != null) {
            for (Integer patientId : caregiverRequestDto.getPatientIds()) {
                Patient patient = patientRepository.findById(patientId)
                        .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

                // Verificar si la relación ya existe
                if (!patientCaregiverRepository.findByPatientIdAndCaregiverId(patient.getId(), caregiver.getId()).isPresent()) {
                    PatientCaregiver patientCaregiver = PatientCaregiver.builder()
                            .patient(patient)
                            .caregiver(caregiver)
                            .build();
                    patientCaregiverRepository.save(patientCaregiver);
                }
            }
        }

        var response = modelMapper.map(caregiver, CaregiverResponseDto.class);
        return new ApiResponse<>("Caregiver created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<CaregiverResponseDto> updateCaregiver(int id, CaregiverRequestDto caregiverRequestDto) {

        Optional<Caregiver> caregiverOptional = caregiverRepository.findById(id);

        if (caregiverOptional.isEmpty()) {
            return new ApiResponse<>("Caregiver not found", Estatus.ERROR, null);
        } else {
            Caregiver caregiver = caregiverOptional.get();
            modelMapper.map(caregiverRequestDto, caregiver);
            caregiver.setAccount(accountRepository.getAccountById(caregiverRequestDto.getAccount()));
            caregiver = caregiverRepository.save(caregiver);

            // Actualizar relaciones con los pacientes
            if (caregiverRequestDto.getPatientIds() != null) {
                for (Integer patientId : caregiverRequestDto.getPatientIds()) {
                    Patient patient = patientRepository.findById(patientId)
                            .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

                    // Verificar si la relación ya existe antes de crearla
                    if (!patientCaregiverRepository.findByPatientIdAndCaregiverId(patient.getId(), caregiver.getId()).isPresent()) {
                        PatientCaregiver patientCaregiver = PatientCaregiver.builder()
                                .patient(patient)
                                .caregiver(caregiver)
                                .build();
                        patientCaregiverRepository.save(patientCaregiver);
                    }
                }
            }

            CaregiverResponseDto response = modelMapper.map(caregiver, CaregiverResponseDto.class);
            return new ApiResponse<>("Caregiver updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<Void> deleteCaregiver(int id) {

        if (caregiverRepository.existsById(id)) {
            caregiverRepository.deleteById(id);
            return new ApiResponse<>("Caregiver deleted successfully", Estatus.SUCCESS, null);
        } else {
            return new ApiResponse<>("Caregiver not found", Estatus.ERROR, null);
        }

    }

}