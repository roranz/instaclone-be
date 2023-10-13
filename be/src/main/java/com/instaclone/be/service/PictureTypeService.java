package com.instaclone.be.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.instaclone.be.entities.PictureType;

public interface PictureTypeService {

    List<PictureType> findAll();

    Optional<PictureType> findById(UUID id);

    PictureType create(PictureType pictureType);

    PictureType update(UUID id, PictureType pictureType);

    void deleteById(UUID id);
    
}