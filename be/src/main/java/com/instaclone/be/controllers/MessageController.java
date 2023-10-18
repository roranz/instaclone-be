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

import com.instaclone.be.dto.MessageDto;
import com.instaclone.be.entities.Message;
import com.instaclone.be.mappers.MessageMapper;
import com.instaclone.be.service.MessageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final MessageService messageService;

    private final MessageMapper messageMapper;

    @GetMapping
    public ResponseEntity<List<MessageDto>> findAll() {
        List<MessageDto> messagesDto = messageMapper.toListDto(messageService.findAll());
        return ResponseEntity.ok(messagesDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDto> findById(@PathVariable UUID id) throws NotFoundException {
        Message message = messageService.findById(id).orElseThrow(() -> new NotFoundException());
        MessageDto messageDto = messageMapper.toDto(message);
        return ResponseEntity.ok(messageDto);   
    }

    @PostMapping
    public ResponseEntity<MessageDto> create(@RequestBody MessageDto messageDto) {
        Message createdMessage = messageService.create(messageMapper.fromDto(messageDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(messageMapper.toDto(createdMessage));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> update(@PathVariable UUID id, @RequestBody MessageDto messageDto) {
        Message updatedMessage = messageService.update(id, messageMapper.fromDto(messageDto));
        return ResponseEntity.ok(messageMapper.toDto(updatedMessage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        messageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
