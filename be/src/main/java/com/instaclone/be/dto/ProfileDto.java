package com.instaclone.be.dto;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

import lombok.Data;

@Data
public class ProfileDto {

    private UUID profileId;

    private UserDto user;

    private String bio;

    private ZonedDateTime insertTimestamp;

    private ZonedDateTime updateTimestamp;

    private Set<PictureDto> pictures;

    private Set<CommentDto> comments;

    private Set<LikeDto> likes;

    private Set<MessageDto> sentMessages;
    
    private Set<MessageDto> receivedMessages;

}