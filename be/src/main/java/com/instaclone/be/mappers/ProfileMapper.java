package com.instaclone.be.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.instaclone.be.dto.ProfileDto;
import com.instaclone.be.entities.Profile;

@Mapper
public interface ProfileMapper {

    ProfileDto toDto(Profile profile);

    Profile fromDto(ProfileDto profileDto);

    List<ProfileDto> toListDto(List<Profile> profiles);

    List<Profile> fromListDto(List<ProfileDto> profilesDto);
    
}
