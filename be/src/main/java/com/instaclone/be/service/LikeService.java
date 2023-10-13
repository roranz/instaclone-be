package com.instaclone.be.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.instaclone.be.entities.Like;

public interface LikeService {

    List<Like> findAll();

    Optional<Like> findById(UUID id);

    Like create(Like like);

    Like update(Like like);

    void deleteById(UUID id);
    
}
