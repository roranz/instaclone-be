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

import com.instaclone.be.entities.Profile;
import com.instaclone.be.service.ProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles/v1")
public class ProfileController {
    
     private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<List<Profile>> findAll() {
        List<Profile> profiles = profileService.findAll();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> findById(@PathVariable UUID id) throws NotFoundException {
        Optional<Profile> profile = profileService.findById(id);
        return profile.map(ResponseEntity::ok).orElseThrow(() -> new NotFoundException());
    }

    @PostMapping
    public ResponseEntity<Profile> create(@RequestBody Profile profile) {
        Profile createdProfile = profileService.create(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profile> update(@PathVariable UUID id, @RequestBody Profile profile) {
        Profile updatedProfile = profileService.update(id, profile);
        return ResponseEntity.ok(updatedProfile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        profileService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
