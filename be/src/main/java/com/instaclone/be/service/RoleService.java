package com.instaclone.be.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.instaclone.be.entities.Role;

public interface RoleService {

    List<Role> findAll();

    Optional<Role> findById(UUID id);

    Role create(Role role);

    Role update(UUID id, Role role);

    void deleteById(UUID id);
        
}
