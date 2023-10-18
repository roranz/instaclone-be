package com.instaclone.be.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.instaclone.be.dto.RoleDto;
import com.instaclone.be.entities.Role;

@Mapper
public interface RoleMapper {

    RoleDto toDto(Role role);

    Role fromDto(RoleDto roleDto);

    List<RoleDto> toListDto(List<Role> roles);

    List<Role> fromListDto(List<RoleDto> rolesDto);

}