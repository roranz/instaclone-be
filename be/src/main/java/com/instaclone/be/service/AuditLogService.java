package com.instaclone.be.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.instaclone.be.entities.AuditLog;

public interface AuditLogService {
    
    List<AuditLog> findAll();

    Optional<AuditLog> findById(UUID id);

    AuditLog create(AuditLog auditLog);

    AuditLog update(AuditLog auditLog);

    void deleteById(UUID id);
    
}
