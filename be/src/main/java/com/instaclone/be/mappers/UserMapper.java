package com.instaclone.be.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.instaclone.be.dto.UserDto;
import com.instaclone.be.entities.User;

@Mapper
public interface UserMapper {

    @Mapping(target = "profileId", source = "profile.profileId")
    UserDto toDto(User user);

    @Mapping(target = "profile.profileId", source = "profileId")
    User fromDto(UserDto userDto);

    List<UserDto> toListDto(List<User> users);

    List<User> fromListDto(List<UserDto> usersDto);

}