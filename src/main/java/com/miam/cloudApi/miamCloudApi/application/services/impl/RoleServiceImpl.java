package com.miam.cloudApi.miamCloudApi.application.services.impl;

import com.miam.cloudApi.miamCloudApi.application.dto.request.RoleRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.RoleResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.RoleService;
import com.miam.cloudApi.miamCloudApi.domain.entities.Role;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.RoleRepository;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import com.miam.cloudApi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<RoleResponseDto> getRoleById(int id) {
        Optional<Role> roleOptional = roleRepository.findById(id);

        if (roleOptional.isEmpty()){
            return new ApiResponse<>("Role not found", Estatus.ERROR, null);
        } else {
            Role role = roleOptional.get();
            var roleResponseDto = modelMapper.map(role, RoleResponseDto.class);
            return new ApiResponse<>("Role found successfully", Estatus.SUCCESS, roleResponseDto);
        }
    }

    @Override
    public ApiResponse<RoleResponseDto> createRole(RoleRequestDto roleRequestDto) {
        var role = modelMapper.map(roleRequestDto, Role.class);
        roleRepository.save(role);
        var roleResponseDto = modelMapper.map(role, RoleResponseDto.class);
        return new ApiResponse<>("Role created successfully", Estatus.SUCCESS, roleResponseDto);
    }

    @Override
    public ApiResponse<RoleResponseDto> updateRole(int id, RoleRequestDto roleRequestDto) {
        Optional<Role> roleOptional = roleRepository.findById(id);

        if (roleOptional.isEmpty()){
            return new ApiResponse<>("Role not found", Estatus.ERROR, null);
        } else {
            Role role = roleOptional.get();
            modelMapper.map(roleRequestDto, role);
            roleRepository.save(role);
            RoleResponseDto roleResponseDto = modelMapper.map(role, RoleResponseDto.class);
            return new ApiResponse<>("Role updated successfully", Estatus.SUCCESS, roleResponseDto);
        }
    }

    @Override
    public ApiResponse<Void> deleteRole(int id) {
        Optional<Role> roleOptional = roleRepository.findById(id);

        if (roleOptional.isEmpty()){
            return new ApiResponse<>("Role not found", Estatus.ERROR, null);
        } else {
            roleRepository.deleteById(id);
            return new ApiResponse<>("Role deleted successfully", Estatus.SUCCESS, null);
        }
    }

}
