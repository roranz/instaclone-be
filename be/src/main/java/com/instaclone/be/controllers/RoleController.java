package com.instaclone.be.controllers;

import java.util.UUID;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instaclone.be.dto.RoleDto;
import com.instaclone.be.entities.Role;
import com.instaclone.be.mappers.RoleMapper;
import com.instaclone.be.service.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {
    
    private final RoleService roleService;

    private final RoleMapper roleMapper;

    @GetMapping
    public ResponseEntity<List<RoleDto>> findAll() {
        List<RoleDto> rolesDto = roleMapper.toListDto(roleService.findAll());
        return ResponseEntity.ok(rolesDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> findById(@PathVariable UUID id) throws NotFoundException {
        Role role = roleService.findById(id).orElseThrow(() -> new NotFoundException());
        RoleDto roleDto = roleMapper.toDto(role);
        return ResponseEntity.ok(roleDto);
    }

    @PostMapping
    public ResponseEntity<RoleDto> create(@RequestBody RoleDto roleDto) {
        Role createdRole = roleService.create(roleMapper.fromDto(roleDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(roleMapper.toDto(createdRole));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> update(@PathVariable UUID id, @RequestBody RoleDto roleDto) {
        Role updatedRole = roleService.update(id, roleMapper.fromDto(roleDto));
        return ResponseEntity.ok(roleMapper.toDto(updatedRole));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        roleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}