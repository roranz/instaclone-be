package com.instaclone.be.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class CommentDto {

    private UUID commentId;

    private UUID pictureId;

    private UUID commenterId;

    private UUID parentCommentId;

    private String commentText;

    private ZonedDateTime insertTimestamp;
    
    private ZonedDateTime updateTimestamp;
    
}