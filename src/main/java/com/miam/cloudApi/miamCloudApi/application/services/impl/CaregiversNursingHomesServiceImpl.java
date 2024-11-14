package com.miam.cloudApi.miamCloudApi.application.services.impl;

import com.miam.cloudApi.miamCloudApi.application.dto.response.CaregiverResponseDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.CaregiversNursingHomesResponseDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.NursingHomeResponseDto;
import com.miam.cloudApi.miamCloudApi.application.serializable.ids.CaregiversNursingHomesId;
import com.miam.cloudApi.miamCloudApi.application.services.CaregiversNursingHomesService;
import com.miam.cloudApi.miamCloudApi.domain.entities.CaregiversNursingHomes;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.CaregiverRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.CaregiversNursingHomesRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.NursingHomeRepository;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import com.miam.cloudApi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CaregiversNursingHomesServiceImpl implements CaregiversNursingHomesService {

    ModelMapper modelMapper;
    CaregiversNursingHomesRepository caregiversNursingHomesRepository;
    CaregiverRepository caregiverRepository;
    NursingHomeRepository nursingHomeRepository;

    public CaregiversNursingHomesServiceImpl(ModelMapper modelMapper, CaregiversNursingHomesRepository caregiversNursingHomesRepository, CaregiverRepository caregiverRepository, NursingHomeRepository nursingHomeRepository) {
        this.modelMapper = modelMapper;
        this.caregiversNursingHomesRepository = caregiversNursingHomesRepository;
        this.caregiverRepository = caregiverRepository;
        this.nursingHomeRepository = nursingHomeRepository;
    }

    @Override
    public ApiResponse<CaregiversNursingHomesResponseDto> getCaregiversNursingHomesById(int caregiverId, int nursingHomeId) {

        Optional<CaregiversNursingHomes> optionalCaregiversNursingHomes = caregiversNursingHomesRepository.findById(new CaregiversNursingHomesId(caregiverId, nursingHomeId));

        if (optionalCaregiversNursingHomes.isPresent()) {
            CaregiverResponseDto caregiverResponseDto = modelMapper.map(caregiverRepository.getCaregiverById(caregiverId), CaregiverResponseDto.class);
            NursingHomeResponseDto nursingHomeResponseDto = modelMapper.map(nursingHomeRepository.getNursingHomeById(nursingHomeId), NursingHomeResponseDto.class);

            CaregiversNursingHomesResponseDto caregiversNursingHomesResponseDto = CaregiversNursingHomesResponseDto.builder()
                    .caregiver(caregiverResponseDto)
                    .nursingHome(nursingHomeResponseDto)
                    .build();

            return new ApiResponse<>("CaregiversNursingHome found successfully", Estatus.SUCCESS, caregiversNursingHomesResponseDto);
        } else {
            return new ApiResponse<>("CaregiversNursingHome not found", Estatus.ERROR, null);
        }

    }

    @Override
    public ApiResponse<List<CaregiversNursingHomesResponseDto>> getCaregiversNursingHomesByNursingHomeId(int id) {
        List<CaregiversNursingHomes> caregiversNursingHomesList = caregiversNursingHomesRepository.getCaregiversNursingHomesByNursingHomeId(id);
        List<CaregiversNursingHomesResponseDto> caregiversNursingHomesResponseDtoList = new ArrayList<>();

        if (caregiversNursingHomesList.isEmpty()) {
            return new ApiResponse<>("CaregiversNursingHomes not found", Estatus.ERROR, null);
        } else {
            for (CaregiversNursingHomes caregiversNursingHomes : caregiversNursingHomesList) {
                CaregiverResponseDto caregiverResponseDto = modelMapper.map(caregiverRepository.getCaregiverById(caregiversNursingHomes.getCaregiver().getId()), CaregiverResponseDto.class);
                NursingHomeResponseDto nursingHomeResponseDto = modelMapper.map(nursingHomeRepository.getNursingHomeById(caregiversNursingHomes.getNursingHome().getId()), NursingHomeResponseDto.class);

                CaregiversNursingHomesResponseDto caregiversNursingHomesResponseDto = CaregiversNursingHomesResponseDto.builder()
                        .caregiver(caregiverResponseDto)
                        .nursingHome(nursingHomeResponseDto)
                        .build();

                caregiversNursingHomesResponseDtoList.add(caregiversNursingHomesResponseDto);
            }

            return new ApiResponse<>("CaregiversNursingHomes found successfully", Estatus.SUCCESS, caregiversNursingHomesResponseDtoList);
        }
    }

}
