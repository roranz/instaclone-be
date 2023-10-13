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

import com.instaclone.be.entities.PictureType;
import com.instaclone.be.service.PictureTypeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/picture-types/v1")
public class PictureTypeController {
    
     private final PictureTypeService pictureTypeService;

    @GetMapping
    public ResponseEntity<List<PictureType>> findAll() {
        List<PictureType> pictureTypes = pictureTypeService.findAll();
        return ResponseEntity.ok(pictureTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PictureType> findById(@PathVariable UUID id) throws NotFoundException {
        Optional<PictureType> pictureType = pictureTypeService.findById(id);
        return pictureType.map(ResponseEntity::ok).orElseThrow(() -> new NotFoundException());
    }

    @PostMapping
    public ResponseEntity<PictureType> create(@RequestBody PictureType pictureType) {
        PictureType createdPictureType = pictureTypeService.create(pictureType);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPictureType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PictureType> update(@PathVariable UUID id, @RequestBody PictureType pictureType) {
        PictureType updatedPictureType = pictureTypeService.update(id, pictureType);
        return ResponseEntity.ok(updatedPictureType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        pictureTypeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}