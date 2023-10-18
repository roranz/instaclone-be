package com.instaclone.be.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class RoleDto {

    private UUID roleId;

    private String description;

    private ZonedDateTime insertTimestamp;
    
    private ZonedDateTime updateTimestamp;
    
}
