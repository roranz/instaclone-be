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

import com.instaclone.be.entities.Picture;
import com.instaclone.be.service.PictureService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pictures/v1")
public class PictureController {
    
    private final PictureService pictureService;

    @GetMapping
    public ResponseEntity<List<Picture>> findAll() {
        List<Picture> pictures = pictureService.findAll();
        return ResponseEntity.ok(pictures);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Picture> findById(@PathVariable UUID id) throws NotFoundException {
        Optional<Picture> picture = pictureService.findById(id);
        return picture.map(ResponseEntity::ok).orElseThrow(() -> new NotFoundException());
    }

    @PostMapping
    public ResponseEntity<Picture> create(@RequestBody Picture picture) {
        Picture createdPicture = pictureService.create(picture);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPicture);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Picture> update(@PathVariable UUID id, @RequestBody Picture picture) {
        Picture updatedPicture = pictureService.update(id, picture);
        return ResponseEntity.ok(updatedPicture);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        pictureService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
