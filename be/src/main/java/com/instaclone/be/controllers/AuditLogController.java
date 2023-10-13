package com.instaclone.be.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instaclone.be.entities.AuditLog;
import com.instaclone.be.service.AuditLogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/audit-logs/v1")
@RequiredArgsConstructor
public class AuditLogController {

     private final AuditLogService auditLogService;

    @GetMapping
    public ResponseEntity<List<AuditLog>> findAll() {
        List<AuditLog> auditLogs = auditLogService.findAll();
        return ResponseEntity.ok(auditLogs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditLog> findById(@PathVariable UUID id) throws NotFoundException {
        Optional<AuditLog> auditLog = auditLogService.findById(id);
        return auditLog.map(ResponseEntity::ok).orElseThrow(() -> new NotFoundException());
    }

    @PostMapping
    public ResponseEntity<AuditLog> create(@RequestBody AuditLog auditLog) {
        AuditLog createdAuditLog = auditLogService.create(auditLog);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuditLog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuditLog> update(@PathVariable UUID id, @RequestBody AuditLog auditLog) {
        AuditLog updatedAuditLog = auditLogService.update(id, auditLog);
        return ResponseEntity.ok(updatedAuditLog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        auditLogService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
