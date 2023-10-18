package com.instaclone.be.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.instaclone.be.dto.UserRoleDto;
import com.instaclone.be.entities.UserRole;

@Mapper
public interface UserRoleMapper {

    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "role.roleId", target = "roleId")
    UserRoleDto toDto(UserRole userRole);

    @Mapping(source = "userId", target = "user.userId")
    @Mapping(source = "roleId", target = "role.roleId")
    UserRole fromDto(UserRoleDto userRoleDto);

    List<UserRoleDto> toListDto(List<UserRole> userRoles);

    List<UserRole> fromListDto(List<UserRoleDto> userRolesDto);

}