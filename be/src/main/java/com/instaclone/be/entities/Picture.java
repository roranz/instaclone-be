package com.instaclone.be.entities;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id")
    private UUID pictureId;

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "profile_id", nullable = false)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "picture_type_id", referencedColumnName = "picture_type_id", nullable = false)
    private PictureType pictureType;

    @Lob
    @Column(name = "picture_data", columnDefinition = "BYTEA", nullable = false)
    private byte[] pictureData;

    @CreatedDate
    @Column(name = "insert_timestamp", nullable = false)
    private ZonedDateTime insertTimestamp;

    @OneToMany(mappedBy = "picture")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "picture")
    private Set<Like> likes;
   
}
