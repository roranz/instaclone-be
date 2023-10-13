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
@Table(name = "comments")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private UUID commentId;

    @ManyToOne
    @JoinColumn(name = "picture_id", referencedColumnName = "picture_id", nullable = false)
    private Picture picture;

    @ManyToOne
    @JoinColumn(name = "commenter_id", referencedColumnName = "profile_id", nullable = false)
    private Profile commenter;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id", referencedColumnName = "comment_id")
    private Comment parentComment;

    @Column(name = "comment_text", nullable = false)
    private String commentText;

    @CreatedDate
    @Column(name = "insert_timestamp", nullable = false)
    private ZonedDateTime insertTimestamp;

    @Column(name = "update_timestamp")
    private ZonedDateTime updateTimestamp;

}