package com.instaclone.be.entities;

import java.time.ZonedDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private UUID profileId;

    @Column(name = "user_id", unique = true)
    private UUID userId;

    @Column(name = "bio")
    private String bio;

    @Column(name = "insert_timestamp")
    private ZonedDateTime insertTimestamp;

    @Column(name = "update_timestamp")
    private ZonedDateTime updateTimestamp;

}