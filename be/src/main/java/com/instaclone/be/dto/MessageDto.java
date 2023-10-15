package com.instaclone.be.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class MessageDto {

    private UUID messageId;

    private UUID senderId;

    private UUID receiverId;

    private String messageText;

    private ZonedDateTime insertTimestamp;

    private ZonedDateTime updateTimestamp;
    
}
