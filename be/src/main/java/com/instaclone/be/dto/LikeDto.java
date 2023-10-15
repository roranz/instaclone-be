package com.instaclone.be.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class LikeDto {

    private UUID likeId;

    private UUID pictureId;

    private UUID profileId;
    
    private ZonedDateTime insertTimestamp;

}
