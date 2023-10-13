package com.instaclone.be.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.instaclone.be.entities.AuditLog;
import com.instaclone.be.repositories.AuditLogRepository;
import com.instaclone.be.service.AuditLogService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;

    @Override
    public List<AuditLog> findAll() {
        return auditLogRepository.findAll();
    }

    @Override
    public Optional<AuditLog> findById(UUID id) {
        return auditLogRepository.findById(id);
    }

    @Override
    public AuditLog create(AuditLog auditLog) {
        return auditLogRepository.save(auditLog);
    }

    @Override
    public AuditLog update(UUID id, AuditLog auditLog) {
        return auditLogRepository.save(auditLog);
    }

    @Override
    public void deleteById(UUID id) {
        auditLogRepository.deleteById(id);
    }

}