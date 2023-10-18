package com.instaclone.be.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.instaclone.be.dto.AuditLogDto;
import com.instaclone.be.entities.AuditLog;

@Mapper
public interface AuditLogMapper {

    AuditLog fromDto(AuditLogDto auditLogDto);

    AuditLogDto toDto(AuditLog auditLog);

    List<AuditLog> fromListDto(List<AuditLogDto> auditLogsDto);

    List<AuditLogDto> toListDto(List<AuditLog> auditLogs);
    
}