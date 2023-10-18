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

import com.instaclone.be.dto.LikeDto;
import com.instaclone.be.entities.Like;
import com.instaclone.be.mappers.LikeMapper;
import com.instaclone.be.service.LikeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/likes")
public class LikeController {

    private final LikeService likeService;

    private final LikeMapper likeMapper;

    @GetMapping
    public ResponseEntity<List<LikeDto>> findAll() {
        List<LikeDto> likesDto = likeMapper.toListDto(likeService.findAll());
        return ResponseEntity.ok(likesDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LikeDto> findById(@PathVariable UUID id) throws NotFoundException {
        Like like = likeService.findById(id).orElseThrow(() -> new NotFoundException());
        LikeDto likeDto = likeMapper.toDto(like);
        return ResponseEntity.ok(likeDto);
    }

    @PostMapping
    public ResponseEntity<LikeDto> create(@RequestBody LikeDto likeDto) {
        Like createdLike = likeService.create(likeMapper.fromDto(likeDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(likeMapper.toDto(createdLike));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LikeDto> update(@PathVariable UUID id, @RequestBody LikeDto likeDto) {
        Like updatedLike = likeService.update(id, likeMapper.fromDto(likeDto));
        return ResponseEntity.ok(likeMapper.toDto(updatedLike));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        likeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
