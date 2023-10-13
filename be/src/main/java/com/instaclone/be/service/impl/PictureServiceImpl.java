package com.instaclone.be.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.instaclone.be.entities.Picture;
import com.instaclone.be.repositories.PictureRepository;
import com.instaclone.be.service.PictureService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PictureServiceImpl implements PictureService {
    
    private final PictureRepository pictureRepository;

    @Override
    public List<Picture> findAll() {
        return pictureRepository.findAll();
    }

    @Override
    public Optional<Picture> findById(UUID id) {
        return pictureRepository.findById(id);
    }

    @Override
    public Picture create(Picture picture) {
        return pictureRepository.save(picture);
    }

    @Override
    public Picture update(Picture picture) {
        return pictureRepository.save(picture);
    }

    @Override
    public void deleteById(UUID id) {
        pictureRepository.deleteById(id);
    }
    
}