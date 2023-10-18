package com.instaclone.be.controllers;

import java.util.List;
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

import com.instaclone.be.dto.AuditLogDto;
import com.instaclone.be.entities.AuditLog;
import com.instaclone.be.mappers.AuditLogMapper;
import com.instaclone.be.service.AuditLogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/audit-logs")
@RequiredArgsConstructor
public class AuditLogController {

    private final AuditLogService auditLogService;

    private final AuditLogMapper auditLogMapper;

    @GetMapping
    public ResponseEntity<List<AuditLogDto>> findAll() {
        List<AuditLogDto> auditLogsDto = auditLogMapper.toListDto(auditLogService.findAll());
        return ResponseEntity.ok(auditLogsDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditLogDto> findById(@PathVariable UUID id) throws NotFoundException {
        AuditLog auditLog = auditLogService.findById(id).orElseThrow(() -> new NotFoundException());
        AuditLogDto auditLogDto = auditLogMapper.toDto(auditLog);
        return ResponseEntity.ok(auditLogDto);
    }

    @PostMapping
    public ResponseEntity<AuditLogDto> create(@RequestBody AuditLogDto auditLogDto) {
        AuditLog createdAuditLog = auditLogService.create(auditLogMapper.fromDto(auditLogDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(auditLogMapper.toDto(createdAuditLog));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuditLogDto> update(@PathVariable UUID id, @RequestBody AuditLogDto auditLogDto) {
        AuditLog updatedAuditLog = auditLogService.update(id, auditLogMapper.fromDto(auditLogDto));
        return ResponseEntity.ok(auditLogMapper.toDto(updatedAuditLog));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        auditLogService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
