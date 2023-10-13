package com.instaclone.be.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.instaclone.be.entities.Like;
import com.instaclone.be.repositories.LikeRepository;
import com.instaclone.be.service.LikeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    @Override
    public List<Like> findAll() {
        return likeRepository.findAll();
    }

    @Override
    public Optional<Like> findById(UUID id) {
        return likeRepository.findById(id);
    }

    @Override
    public Like create(Like like) {
        return likeRepository.save(like);
    }

    @Override
    public Like update(UUID id, Like like) {
        return likeRepository.save(like);
    }

    @Override
    public void deleteById(UUID id) {
        likeRepository.deleteById(id);
    }

}