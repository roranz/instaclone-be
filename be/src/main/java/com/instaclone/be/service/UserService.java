package com.instaclone.be.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.instaclone.be.entities.User;

public interface UserService {
    
    List<User> findAll();

    Optional<User> findById(UUID id);

    User create(User user);

    User update(UUID id, User user);

    void deleteById(UUID id);

}