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

import com.instaclone.be.dto.PictureTypeDto;
import com.instaclone.be.entities.PictureType;
import com.instaclone.be.mappers.PictureTypeMapper;
import com.instaclone.be.service.PictureTypeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/picture-types")
public class PictureTypeController {
    
    private final PictureTypeService pictureTypeService;

    private final PictureTypeMapper pictureTypeMapper;

    @GetMapping
    public ResponseEntity<List<PictureTypeDto>> findAll() {
        List<PictureTypeDto> pictureTypesDto = pictureTypeMapper.toListDto(pictureTypeService.findAll());
        return ResponseEntity.ok(pictureTypesDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PictureTypeDto> findById(@PathVariable UUID id) throws NotFoundException {
        PictureType pictureType = pictureTypeService.findById(id).orElseThrow(() -> new NotFoundException());
        PictureTypeDto pictureTypeDto = pictureTypeMapper.toDto(pictureType);
        return ResponseEntity.ok(pictureTypeDto);
    }

    @PostMapping
    public ResponseEntity<PictureTypeDto> create(@RequestBody PictureTypeDto pictureTypeDto) {
        PictureType createdPictureType = pictureTypeService.create(pictureTypeMapper.fromDto(pictureTypeDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(pictureTypeMapper.toDto(createdPictureType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PictureTypeDto> update(@PathVariable UUID id, @RequestBody PictureTypeDto pictureTypeDto) {
        PictureType updatedPictureType = pictureTypeService.update(id, pictureTypeMapper.fromDto(pictureTypeDto));
        return ResponseEntity.ok(pictureTypeMapper.toDto(updatedPictureType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        pictureTypeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}