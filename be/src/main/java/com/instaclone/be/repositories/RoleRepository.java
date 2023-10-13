package com.instaclone.be.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instaclone.be.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {}
