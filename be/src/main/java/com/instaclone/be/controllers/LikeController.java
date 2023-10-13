package com.instaclone.be.controllers;

import java.util.List;
import java.util.Optional;
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

import com.instaclone.be.entities.Like;
import com.instaclone.be.service.LikeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes/v1")
public class LikeController {

    private final LikeService likeService;

    @GetMapping
    public ResponseEntity<List<Like>> findAll() {
        List<Like> likes = likeService.findAll();
        return ResponseEntity.ok(likes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Like> findById(@PathVariable UUID id) throws NotFoundException {
        Optional<Like> like = likeService.findById(id);
        return like.map(ResponseEntity::ok).orElseThrow(() -> new NotFoundException());
    }

    @PostMapping
    public ResponseEntity<Like> create(@RequestBody Like like) {
        Like createdLike = likeService.create(like);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLike);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Like> update(@PathVariable UUID id, @RequestBody Like like) {
        Like updatedLike = likeService.update(id, like);
        return ResponseEntity.ok(updatedLike);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        likeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
