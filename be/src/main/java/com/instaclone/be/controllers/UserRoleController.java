package com.instaclone.be.controllers;

import java.util.List;
import java.util.UUID;

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

import com.instaclone.be.dto.UserRoleDto;
import com.instaclone.be.entities.UserRole;
import com.instaclone.be.mappers.UserRoleMapper;
import com.instaclone.be.service.UserRoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-roles")
public class UserRoleController {
    
    private final UserRoleService userRoleService;

    private final UserRoleMapper userRoleMapper;

    @GetMapping
    public ResponseEntity<List<UserRoleDto>> findAll() {
        List<UserRoleDto> userRolesDto = userRoleMapper.toListDto(userRoleService.findAll());
        return ResponseEntity.ok(userRolesDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoleDto> findById(@PathVariable UUID id) throws NotFoundException {
        UserRole userRole = userRoleService.findById(id).orElseThrow(() -> new NotFoundException());
        UserRoleDto userRoleDto = userRoleMapper.toDto(userRole);
        return ResponseEntity.ok(userRoleDto);
    }

    @PostMapping
    public ResponseEntity<UserRoleDto> create(@RequestBody UserRoleDto userRoleDto) {
        UserRole createdUserRole = userRoleService.create(userRoleMapper.fromDto(userRoleDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(userRoleMapper.toDto(createdUserRole));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRoleDto> update(@PathVariable UUID id, @RequestBody UserRoleDto userRoleDto) {
        UserRole updatedUserRole = userRoleService.update(id, userRoleMapper.fromDto(userRoleDto));
        return ResponseEntity.ok(userRoleMapper.toDto(updatedUserRole));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        userRoleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
