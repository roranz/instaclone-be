package com.instaclone.be.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.instaclone.be.entities.Comment;
import com.instaclone.be.repositories.CommentRepository;
import com.instaclone.be.service.CommentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(UUID id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(UUID id, Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteById(UUID id) {
        commentRepository.deleteById(id);
    }
    
}