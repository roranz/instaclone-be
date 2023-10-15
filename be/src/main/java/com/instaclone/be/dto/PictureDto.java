package com.instaclone.be.dto;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

import lombok.Data;

@Data
public class PictureDto {

    private UUID pictureId;

    private UUID profileId;

    private UUID pictureTypeId;

    private byte[] pictureData;

    private ZonedDateTime insertTimestamp;

    private Set<CommentDto> comments;

    private Set<LikeDto> likes;
    
}