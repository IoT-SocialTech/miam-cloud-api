package com.miam.cloudApi.miamCloudApi.application.controller;

import com.miam.cloudApi.miamCloudApi.application.dto.request.RoleRequestDto;
import com.miam.cloudApi.miamCloudApi.application.dto.response.RoleResponseDto;
import com.miam.cloudApi.miamCloudApi.application.services.RoleService;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Role Controller", description = "Role Controller API")
@RestController
@RequestMapping("/api/v1/miam-cloud-api")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Operation(summary = "Get role by id")
    @GetMapping("/roles/{id}")
    public ResponseEntity<ApiResponse<RoleResponseDto>> getRoleById(@PathVariable int id) {
        var res = roleService.getRoleById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Create role")
    @GetMapping("/roles")
    public ResponseEntity<ApiResponse<RoleResponseDto>> createRole(@RequestBody RoleRequestDto roleRequestDto) {
        var res = roleService.createRole(roleRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update role")
    @PutMapping("/roles/{id}")
    public ResponseEntity<ApiResponse<RoleResponseDto>> updateRole(@PathVariable int id, @RequestBody RoleRequestDto roleRequestDto) {
        var res = roleService.updateRole(id, roleRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Delete role")
    @DeleteMapping("/roles/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRole(@PathVariable int id) {
        var res = roleService.deleteRole(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
