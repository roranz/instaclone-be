package com.instaclone.be.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class AuditLogDto {

    private UUID auditLogId;

    private UUID userId;

    private String action;

    private String tableName;

    private UUID recordId;

    private ZonedDateTime insertTimestamp;
    
}