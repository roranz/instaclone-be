package com.instaclone.be.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.instaclone.be.entities.PictureType;
import com.instaclone.be.repositories.PictureTypeRepository;
import com.instaclone.be.service.PictureTypeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PictureTypeServiceImpl implements PictureTypeService {
    
  private final PictureTypeRepository pictureTypeRepository;

  @Override
  public List<PictureType> findAll() {
    return pictureTypeRepository.findAll();
  }

  @Override
  public Optional<PictureType> findById(UUID id) {
    return pictureTypeRepository.findById(id);
  }

  @Override
  public PictureType create(PictureType pictureType) {
    return pictureTypeRepository.save(pictureType);
  }

  @Override
  public PictureType update(UUID id, PictureType pictureType) {
    return pictureTypeRepository.save(pictureType);
  }

  @Override
  public void deleteById(UUID id) {
    pictureTypeRepository.deleteById(id);
  }
  
}