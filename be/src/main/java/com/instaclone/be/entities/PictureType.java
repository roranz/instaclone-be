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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "pictures_types")
public class PictureType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_type_id")
    private UUID pictureTypeId;

    @Column(name = "description", unique = true, nullable = false)
    private String description;

    @CreatedDate
    @Column(name = "insert_timestamp", nullable = false)
    private ZonedDateTime insertTimestamp;

    @Column(name = "update_timestamp")
    private ZonedDateTime updateTimestamp;

    @OneToMany(mappedBy = "pictureType")
    private Set<Picture> pictures;
    
}
