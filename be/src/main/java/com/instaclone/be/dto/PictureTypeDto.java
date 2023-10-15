package com.instaclone.be.dto;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

import lombok.Data;

@Data
public class PictureTypeDto {

    private UUID pictureTypeId;
    
    private String description;

    private ZonedDateTime insertTimestamp;

    private ZonedDateTime updateTimestamp;

    private Set<PictureDto> pictures;
    
}