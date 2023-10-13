package com.instaclone.be.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.instaclone.be.entities.UserRole;

public interface UserRoleService {

    List<UserRole> findAll();

    Optional<UserRole> findById(UUID id);

    UserRole create(UserRole userRole);

    UserRole update(UUID id, UserRole userRole);

    void deleteById(UUID id);
    
}