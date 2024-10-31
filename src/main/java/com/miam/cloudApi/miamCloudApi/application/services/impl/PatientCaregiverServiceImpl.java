package com.miam.cloudApi.miamCloudApi.application.services.impl;

import com.miam.cloudApi.miamCloudApi.application.dto.response.CaregiverResponseDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.PatientCaregiverResponseDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.PatientResponseDto;
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

import java.util.ArrayList;
import java.util.List;
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
            PatientResponseDto patientResponseDto = modelMapper.map(patientRepository.getPatientsById(patient), PatientResponseDto.class);
            CaregiverResponseDto caregiverResponseDto = modelMapper.map(caregiverRepository.getCaregiverById(caregiver), CaregiverResponseDto.class);

            PatientCaregiverResponseDto patientCaregiverResponseDto = PatientCaregiverResponseDto.builder()
                    .patient(patientResponseDto)
                    .caregiver(caregiverResponseDto)
                    .build();
            return new ApiResponse<>("PatientCaregiver found successfully", Estatus.SUCCESS, patientCaregiverResponseDto);
        } else {
            return new ApiResponse<>("PatientCaregiver not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<List<PatientCaregiverResponseDto>> getPatientCaregiverByCaregiverId(int id) {
        List<PatientCaregiver> patientCaregivers = patientCaregiverRepository.getPatientCaregiverByCaregiverId(id);
        List<PatientCaregiverResponseDto> patientCaregiverResponseDtoList = new ArrayList<>();
        if (patientCaregivers.isEmpty()) {
            return new ApiResponse<>("PatientCaregiver not found", Estatus.ERROR, null);
        } else {
            for(PatientCaregiver patientCaregiver : patientCaregivers){
                PatientResponseDto patientResponseDto = modelMapper.map(patientRepository.getPatientsById(patientCaregiver.getPatient().getId()), PatientResponseDto.class);
                CaregiverResponseDto caregiverResponseDto = modelMapper.map(caregiverRepository.getCaregiverById(patientCaregiver.getCaregiver().getId()), CaregiverResponseDto.class);
                PatientCaregiverResponseDto patientCaregiverResponseDto = PatientCaregiverResponseDto.builder()
                        .patient(patientResponseDto)
                        .caregiver(caregiverResponseDto)
                        .build();
                patientCaregiverResponseDtoList.add(patientCaregiverResponseDto);
            }
            return new ApiResponse<>("PatientCaregiver found successfully", Estatus.SUCCESS, patientCaregiverResponseDtoList);
        }
    }

    @Override
    public ApiResponse<List<PatientCaregiverResponseDto>> getPatientCaregiverByPatientId(int id) {
        List<PatientCaregiver> patientCaregivers = patientCaregiverRepository.getPatientCaregiverByPatientId(id);
        List<PatientCaregiverResponseDto> patientCaregiverResponseDtoList = new ArrayList<>();
        if (patientCaregivers.isEmpty()) {
            return new ApiResponse<>("PatientCaregiver not found", Estatus.ERROR, null);
        } else {
            for(PatientCaregiver patientCaregiver : patientCaregivers){
                PatientResponseDto patientResponseDto = modelMapper.map(patientRepository.getPatientsById(patientCaregiver.getPatient().getId()), PatientResponseDto.class);
                CaregiverResponseDto caregiverResponseDto = modelMapper.map(caregiverRepository.getCaregiverById(patientCaregiver.getCaregiver().getId()), CaregiverResponseDto.class);
                PatientCaregiverResponseDto patientCaregiverResponseDto = PatientCaregiverResponseDto.builder()
                        .patient(patientResponseDto)
                        .caregiver(caregiverResponseDto)
                        .build();
                patientCaregiverResponseDtoList.add(patientCaregiverResponseDto);
            }
            return new ApiResponse<>("PatientCaregiver found successfully", Estatus.SUCCESS, patientCaregiverResponseDtoList);
        }
    }

}
