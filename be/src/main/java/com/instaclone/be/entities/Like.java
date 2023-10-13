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
@Table(name = "likes")
@Data
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id", nullable = false)
    private UUID likeId;

    @ManyToOne
    @JoinColumn(name = "picture_id", referencedColumnName = "picture_id")
    private Picture picture;

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "profile_id")
    private Profile profile;

    @CreatedDate
    @Column(name = "insert_timestamp", nullable = false)
    private ZonedDateTime insertTimestamp;
    
}