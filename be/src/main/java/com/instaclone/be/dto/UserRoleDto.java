package com.instaclone.be.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class UserRoleDto {

    private UUID id;

    private UUID userId;

    private UUID roleId;

    private ZonedDateTime insertTimestamp;
    
}