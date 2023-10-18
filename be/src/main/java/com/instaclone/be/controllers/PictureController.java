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

import com.instaclone.be.dto.PictureDto;
import com.instaclone.be.entities.Picture;
import com.instaclone.be.mappers.PictureMapper;
import com.instaclone.be.service.PictureService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pictures")
public class PictureController {
    
    private final PictureService pictureService;

    private final PictureMapper pictureMapper;

    @GetMapping
    public ResponseEntity<List<PictureDto>> findAll() {
        List<PictureDto> picturesDto = pictureMapper.toListDto(pictureService.findAll());
        return ResponseEntity.ok(picturesDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PictureDto> findById(@PathVariable UUID id) throws NotFoundException {
        Picture picture = pictureService.findById(id).orElseThrow(() -> new NotFoundException());
        PictureDto pictureDto = pictureMapper.toDto(picture);
        return ResponseEntity.ok(pictureDto);
    }

    @PostMapping
    public ResponseEntity<PictureDto> create(@RequestBody PictureDto pictureDto) {
        Picture createdPicture = pictureService.create(pictureMapper.fromDto(pictureDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(pictureMapper.toDto(createdPicture));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PictureDto> update(@PathVariable UUID id, @RequestBody PictureDto pictureDto) {
        Picture updatedPicture = pictureService.update(id, pictureMapper.fromDto(pictureDto));
        return ResponseEntity.ok(pictureMapper.toDto(updatedPicture));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        pictureService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
