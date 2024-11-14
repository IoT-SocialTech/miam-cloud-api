package com.miam.cloudApi.miamCloudApi.application.services.impl;

import com.miam.cloudApi.miamCloudApi.application.dto.request.NursingHomeRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.NursingHomeResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.NursingHomeService;
import com.miam.cloudApi.miamCloudApi.domain.entities.Caregiver;
import com.miam.cloudApi.miamCloudApi.domain.entities.CaregiversNursingHomes;
import com.miam.cloudApi.miamCloudApi.domain.entities.NursingHome;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.AccountRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.CaregiverRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.CaregiversNursingHomesRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.NursingHomeRepository;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import com.miam.cloudApi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NursingHomeServiceImpl implements NursingHomeService {

    private final CaregiversNursingHomesRepository caregiversNursingHomesRepository;
    private final NursingHomeRepository nursingHomeRepository;
    private final ModelMapper modelMapper;
    private final AccountRepository accountRepository;
    private final CaregiverRepository caregiverRepository;

    public NursingHomeServiceImpl(NursingHomeRepository nursingHomeRepository, ModelMapper modelMapper,
                                  AccountRepository accountRepository, CaregiverRepository caregiverRepository, CaregiversNursingHomesRepository caregiversNursingHomesRepository) {
        this.nursingHomeRepository = nursingHomeRepository;
        this.modelMapper = modelMapper;
        this.accountRepository = accountRepository;
        this.caregiverRepository = caregiverRepository;
        this.caregiversNursingHomesRepository = caregiversNursingHomesRepository;
    }

    @Override
    public ApiResponse<NursingHomeResponseDto> getNursingHomeById(int id) {
        Optional<NursingHome> nursingHomeOptional = nursingHomeRepository.findById(id);

        if (nursingHomeOptional.isEmpty()){
            return new ApiResponse<>("Nursing Home not found", Estatus.ERROR, null);
        } else {
            NursingHome nursingHome = nursingHomeOptional.get();
            NursingHomeResponseDto response = modelMapper.map(nursingHome, NursingHomeResponseDto.class);
            return new ApiResponse<>("Nursing Home found successfully", Estatus.SUCCESS, response);
        }

    }

    @Override
    public ApiResponse<NursingHomeResponseDto> createNursingHome(NursingHomeRequestDto nursingHomeRequestDto) {
        var nursingHome = modelMapper.map(nursingHomeRequestDto, NursingHome.class);
        nursingHome.setAccount(accountRepository.getAccountById(nursingHomeRequestDto.getAccount()));
        nursingHomeRepository.save(nursingHome);

        if (nursingHomeRequestDto.getCaregiverIds() != null){
            for (int caregiverId : nursingHomeRequestDto.getCaregiverIds()){
                Caregiver caregiver = caregiverRepository.findById(caregiverId)
                        .orElseThrow(() -> new RuntimeException("Caregiver not found"));

                if (!caregiversNursingHomesRepository.findByCaregiverIdAndNursingHomeId(caregiver.getId(), nursingHome.getId()).isPresent()){
                    CaregiversNursingHomes caregiversNursingHomes = CaregiversNursingHomes.builder()
                            .caregiver(caregiver)
                            .nursingHome(nursingHome)
                            .build();
                    caregiversNursingHomesRepository.save(caregiversNursingHomes);
                } else {
                    return new ApiResponse<>("Caregiver already assigned to a Nursing Home", Estatus.ERROR, null);
                }
            }
        }

        var response = modelMapper.map(nursingHome, NursingHomeResponseDto.class);

        return new ApiResponse<>("Nursing Home created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<NursingHomeResponseDto> updateNursingHome(int id, NursingHomeRequestDto nursingHomeRequestDto) {
        Optional<NursingHome> optionalNursingHome = nursingHomeRepository.findById(id);

        if (optionalNursingHome.isEmpty()){
            return new ApiResponse<>("Nursing Home not found", Estatus.ERROR, null);
        } else {
            NursingHome nursingHome = optionalNursingHome.get();
            modelMapper.map(nursingHomeRequestDto, nursingHome);
            nursingHome.setAccount(accountRepository.getAccountById(nursingHomeRequestDto.getAccount()));
            nursingHomeRepository.save(nursingHome);

            if (nursingHomeRequestDto.getCaregiverIds() != null) {
                for (int caregiverId : nursingHomeRequestDto.getCaregiverIds()) {
                    Caregiver caregiver = caregiverRepository.findById(caregiverId)
                            .orElseThrow(() -> new RuntimeException("Caregiver not found"));

                    if (!caregiversNursingHomesRepository.findByCaregiverIdAndNursingHomeId(caregiver.getId(), nursingHome.getId()).isPresent()) {
                        CaregiversNursingHomes caregiversNursingHomes = CaregiversNursingHomes.builder()
                                .caregiver(caregiver)
                                .nursingHome(nursingHome)
                                .build();
                        caregiversNursingHomesRepository.save(caregiversNursingHomes);
                    } else {
                        return new ApiResponse<>("Caregiver already assigned to a Nursing Home", Estatus.ERROR, null);
                    }
                }
            }

            var response = modelMapper.map(nursingHome, NursingHomeResponseDto.class);

            return new ApiResponse<>("Nursing Home updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<Void> deleteNursingHome(int id) {

        if (nursingHomeRepository.existsById(id)){
            nursingHomeRepository.deleteById(id);
            return new ApiResponse<>("Nursing Home deleted successfully", Estatus.SUCCESS, null);
        } else {
            return new ApiResponse<>("Nursing Home not found", Estatus.ERROR, null);
        }

    }

}
