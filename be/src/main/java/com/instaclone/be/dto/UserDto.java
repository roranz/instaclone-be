package com.instaclone.be.dto;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

import lombok.Data;

@Data
public class UserDto {

    private UUID userId;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String passwordHash;

    private ZonedDateTime insertTimestamp;

    private ZonedDateTime updateTimestamp;

    private UUID profileId;

    private Set<UserRoleDto> userRoles;
    
}