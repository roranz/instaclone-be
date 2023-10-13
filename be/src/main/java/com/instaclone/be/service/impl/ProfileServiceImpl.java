package com.instaclone.be.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.instaclone.be.entities.Profile;
import com.instaclone.be.repositories.ProfileRepository;
import com.instaclone.be.service.ProfileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    
    private final ProfileRepository profileRepository;

    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public Optional<Profile> findById(UUID id) {
        return profileRepository.findById(id);
    }

    @Override
    public Profile create(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile update(UUID id, Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public void deleteById(UUID id) {
        profileRepository.deleteById(id);
    }

}