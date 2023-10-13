package com.instaclone.be.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instaclone.be.entities.AuditLog;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, UUID> {}
