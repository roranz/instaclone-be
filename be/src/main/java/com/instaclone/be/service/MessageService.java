package com.instaclone.be.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.instaclone.be.entities.Message;

public interface MessageService {

    List<Message> findAll();

    Optional<Message> findById(UUID id);

    Message create(Message message);

    Message update(Message message);

    void deleteById(UUID id);
    
}
