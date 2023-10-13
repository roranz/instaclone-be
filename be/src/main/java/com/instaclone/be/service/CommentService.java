package com.instaclone.be.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.instaclone.be.entities.Comment;

public interface CommentService {

    List<Comment> findAll();

    Optional<Comment> findById(UUID id);

    Comment create(Comment comment);

    Comment update(Comment comment);

    void deleteById(UUID id);
    
}
