package com.instaclone.be.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.instaclone.be.entities.Picture;

public interface PictureService {

    List<Picture> findAll();

    Optional<Picture> findById(UUID id);

    Picture create(Picture picture);

    Picture update(Picture picture);

    void deleteById(UUID id);
    
}
