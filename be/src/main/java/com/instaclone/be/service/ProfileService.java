package com.instaclone.be.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.instaclone.be.entities.Profile;

public interface ProfileService {
    
    List<Profile> findAll();

    Optional<Profile> findById(UUID id);

    Profile create(Profile profile);

    Profile update(UUID id, Profile profile);

    void deleteById(UUID id);

}
