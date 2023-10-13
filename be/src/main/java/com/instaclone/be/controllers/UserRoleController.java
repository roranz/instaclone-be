package com.instaclone.be.controllers;

import java.util.List;
import java.util.Optional;
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

import com.instaclone.be.entities.UserRole;
import com.instaclone.be.service.UserRoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-roles/v1")
public class UserRoleController {
    
    private final UserRoleService userRoleService;

    @GetMapping
    public ResponseEntity<List<UserRole>> findAll() {
        List<UserRole> userRoles = userRoleService.findAll();
        return ResponseEntity.ok(userRoles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRole> findById(@PathVariable UUID id) throws NotFoundException {
        Optional<UserRole> userRole = userRoleService.findById(id);
        return userRole.map(ResponseEntity::ok).orElseThrow(() -> new NotFoundException());
    }

    @PostMapping
    public ResponseEntity<UserRole> create(@RequestBody UserRole userRole) {
        UserRole createdUserRole = userRoleService.create(userRole);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRole> update(@PathVariable UUID id, @RequestBody UserRole userRole) {
        UserRole updatedUserRole = userRoleService.update(id, userRole);
        return ResponseEntity.ok(updatedUserRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        userRoleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
