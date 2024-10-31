package com.miam.cloudApi.miamCloudApi.application.services.impl;

import com.miam.cloudApi.miamCloudApi.application.dto.request.PatientRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.PatientResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.PatientService;
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
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final AccountRepository accountRepository;
    private final CaregiverRepository caregiverRepository;
    private final PatientCaregiverRepository patientCaregiverRepository;
    private final ModelMapper modelMapper;

    public PatientServiceImpl(PatientRepository patientRepository, AccountRepository accountRepository,
                              CaregiverRepository caregiverRepository, PatientCaregiverRepository patientCaregiverRepository,
                              ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.accountRepository = accountRepository;
        this.caregiverRepository = caregiverRepository;
        this.patientCaregiverRepository = patientCaregiverRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<PatientResponseDto> getPatientById(int id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isEmpty()){
            return new ApiResponse<>("Patient not found", Estatus.ERROR,null);
        } else {
            Patient patient = optionalPatient.get();
            PatientResponseDto patientResponseDto = modelMapper.map(patient, PatientResponseDto.class);
            return new ApiResponse<>("Patient found successfully", Estatus.SUCCESS, patientResponseDto);
        }
    }

    @Override
    public ApiResponse<PatientResponseDto> createPatient(PatientRequestDto patientRequestDto) {

        var patient = modelMapper.map(patientRequestDto, Patient.class);
        patient.setAccount(accountRepository.getAccountById(patientRequestDto.getAccount()));
        patient = patientRepository.save(patient);

        // Crear relaciones en PatientCaregiver si hay IDs de cuidadores
        if (patientRequestDto.getCaregiverIds() != null) {
            for (Integer caregiverId : patientRequestDto.getCaregiverIds()) {
                Caregiver caregiver = caregiverRepository.findById(caregiverId)
                        .orElseThrow(() -> new ResourceNotFoundException("Caregiver not found"));

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
        var response = modelMapper.map(patient, PatientResponseDto.class);
        return new ApiResponse<>("Patient created successfully", Estatus.SUCCESS, response);

    }

    @Override
    public ApiResponse<PatientResponseDto> updatePatient(int id, PatientRequestDto patientRequestDto) {

        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isEmpty()){
            return new ApiResponse<>("Patient not found", Estatus.ERROR, null);
        } else {
            Patient patient = optionalPatient.get();
            modelMapper.map(patientRequestDto, patient);
            patient.setAccount(accountRepository.getAccountById(patientRequestDto.getAccount()));
            patient = patientRepository.save(patient);

            // Actualizar relaciones con los cuidadores
            if (patientRequestDto.getCaregiverIds() != null) {
                for (Integer caregiverId : patientRequestDto.getCaregiverIds()) {
                    Caregiver caregiver = caregiverRepository.findById(caregiverId)
                            .orElseThrow(() -> new ResourceNotFoundException("Caregiver not found"));

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

            PatientResponseDto patientResponseDto = modelMapper.map(patient, PatientResponseDto.class);
            return new ApiResponse<>("Patient updated successfully", Estatus.SUCCESS, patientResponseDto);
        }
    }

    @Override
    public ApiResponse<Void> deletePatient(int id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isEmpty()){
            return new ApiResponse<>("Patient not found", Estatus.ERROR, null);
        } else {
            patientRepository.deleteById(id);
            return new ApiResponse<>("Patient deleted successfully", Estatus.SUCCESS, null);
        }
    }

}
