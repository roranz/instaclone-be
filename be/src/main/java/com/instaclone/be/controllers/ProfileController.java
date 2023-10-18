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

import com.instaclone.be.dto.ProfileDto;
import com.instaclone.be.entities.Profile;
import com.instaclone.be.mappers.ProfileMapper;
import com.instaclone.be.service.ProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profiles")
public class ProfileController {
    
    private final ProfileService profileService;

    private final ProfileMapper profileMapper;

    @GetMapping
    public ResponseEntity<List<ProfileDto>> findAll() {
        List<ProfileDto> profilesDto = profileMapper.toListDto(profileService.findAll());
        return ResponseEntity.ok(profilesDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> findById(@PathVariable UUID id) throws NotFoundException {
        Profile profile = profileService.findById(id).orElseThrow(() -> new NotFoundException());
        ProfileDto profileDto = profileMapper.toDto(profile);
        return ResponseEntity.ok(profileDto);
    }

    @PostMapping
    public ResponseEntity<ProfileDto> create(@RequestBody ProfileDto profileDto) {
        Profile createdProfile = profileService.create(profileMapper.fromDto(profileDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(profileMapper.toDto(createdProfile));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileDto> update(@PathVariable UUID id, @RequestBody ProfileDto profileDto) {
        Profile updatedProfile = profileService.update(id, profileMapper.fromDto(profileDto));
        return ResponseEntity.ok(profileMapper.toDto(updatedProfile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        profileService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
