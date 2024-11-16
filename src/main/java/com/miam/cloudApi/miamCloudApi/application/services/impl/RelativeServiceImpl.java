package com.miam.cloudApi.miamCloudApi.application.services.impl;

import com.miam.cloudApi.miamCloudApi.application.dto.request.RelativeRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.RelativeResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.RelativeService;
import com.miam.cloudApi.miamCloudApi.domain.entities.Relative;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.AccountRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.RelativeRepository;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import com.miam.cloudApi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RelativeServiceImpl implements RelativeService {

    private final RelativeRepository relativeRepository;
    private final ModelMapper modelMapper;
    private final AccountRepository accountRepository;

    public RelativeServiceImpl(RelativeRepository relativeRepository, ModelMapper modelMapper, AccountRepository accountRepository) {
        this.relativeRepository = relativeRepository;
        this.modelMapper = modelMapper;
        this.accountRepository = accountRepository;
    }

    @Override
    public ApiResponse<RelativeResponseDto> getRelativeById(int id) {
        Optional<Relative> optionalRelative = relativeRepository.findById(id);

        if (optionalRelative.isEmpty()) {
            return new ApiResponse<>("Relative not found", Estatus.ERROR, null);
        } else {
            Relative relative = optionalRelative.get();
            RelativeResponseDto relativeResponseDto = modelMapper.map(relative, RelativeResponseDto.class);
            return new ApiResponse<>("Relative found successfully", Estatus.SUCCESS, relativeResponseDto);
        }
    }

    @Override
    public ApiResponse<RelativeResponseDto> getRelativeByAccountId(int id) {
        Relative relative = relativeRepository.getRelativeByAccountId(id);

        if (relative == null) {
            return new ApiResponse<>("Relative not found", Estatus.ERROR, null);
        } else {
            RelativeResponseDto response = modelMapper.map(relative, RelativeResponseDto.class);
            return new ApiResponse<>("Relative found successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<RelativeResponseDto> createRelative(RelativeRequestDto relativeRequestDto) {
        var relative = modelMapper.map(relativeRequestDto, Relative.class);
        relative.setAccount(accountRepository.getAccountById(relativeRequestDto.getAccount()));
        relative = relativeRepository.save(relative);

        return new ApiResponse<>("Relative created successfully", Estatus.SUCCESS, modelMapper.map(relative, RelativeResponseDto.class));
    }

    @Override
    public ApiResponse<RelativeResponseDto> updateRelative(int id, RelativeRequestDto relativeRequestDto) {
        Optional<Relative> optionalRelative = relativeRepository.findById(id);

        if (optionalRelative.isEmpty()) {
            return new ApiResponse<>("Relative not found", Estatus.ERROR, null);
        } else {
            Relative relative = optionalRelative.get();
            modelMapper.map(relativeRequestDto, relative);
            relative.setAccount(accountRepository.getAccountById(relativeRequestDto.getAccount()));
            relative = relativeRepository.save(relative);

            return new ApiResponse<>("Relative updated successfully", Estatus.SUCCESS, modelMapper.map(relative, RelativeResponseDto.class));
        }
    }

    @Override
    public ApiResponse<Void> deleteRelative(int id) {
        Optional<Relative> optionalRelative = relativeRepository.findById(id);

        if (optionalRelative.isEmpty()) {
            return new ApiResponse<>("Relative not found", Estatus.ERROR, null);
        } else {
            relativeRepository.delete(optionalRelative.get());
            return new ApiResponse<>("Relative deleted successfully", Estatus.SUCCESS, null);
        }
    }

}
