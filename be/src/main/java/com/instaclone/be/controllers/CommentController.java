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

import com.instaclone.be.dto.CommentDto;
import com.instaclone.be.entities.AuditLog;
import com.instaclone.be.entities.Comment;
import com.instaclone.be.mappers.CommentMapper;
import com.instaclone.be.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    
    private final CommentService commentService;

    private final CommentMapper commentMapper;

    @GetMapping
    public ResponseEntity<List<CommentDto>> findAll() {
         List<CommentDto> commentsDto = commentMapper.toListDto(commentService.findAll());
        return ResponseEntity.ok(commentsDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> findById(@PathVariable UUID id) throws NotFoundException {
        Comment comment = commentService.findById(id).orElseThrow(() -> new NotFoundException());
        CommentDto commentDto = commentMapper.toDto(comment);
        return ResponseEntity.ok(commentDto);
    }

    @PostMapping
    public ResponseEntity<CommentDto> create(@RequestBody CommentDto commentDto) {
        Comment createdComment = commentService.create(commentMapper.fromDto(commentDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(commentMapper.toDto(createdComment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable UUID id, @RequestBody CommentDto commentDto) {
        Comment updatedComment = commentService.update(id, commentMapper.fromDto(commentDto));
        return ResponseEntity.ok(commentMapper.toDto(updatedComment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        commentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}