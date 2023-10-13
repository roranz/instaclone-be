package com.instaclone.be.entities;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "messages")
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", nullable = false)
    private UUID messageId;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "profile_id", nullable = false)
    private Profile sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "profile_id", nullable = false)
    private Profile receiver;

    @Column(name = "message_text", nullable = false)
    private String messageText;

    @CreatedDate
    @Column(name = "insert_timestamp", nullable = false)
    private ZonedDateTime insertTimestamp;

    @Column(name = "update_timestamp")
    private ZonedDateTime updateTimestamp;

}