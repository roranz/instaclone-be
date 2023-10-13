package com.instaclone.be.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instaclone.be.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {}
